package com.project.lms.services;

import com.project.lms.constants.LibraryConstants;
import com.project.lms.entities.dao.Book;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dao.IssuedBook;
import com.project.lms.entities.dao.Student;
import com.project.lms.entities.dto.IssuedBookDto;
import com.project.lms.exceptions.EmployeeNotFoundException;
import com.project.lms.exceptions.EmployeeRoleNotSatisfied;
import com.project.lms.repositories.BookRepository;
import com.project.lms.repositories.EmployeeRepository;
import com.project.lms.repositories.IssuedBookRepository;
import com.project.lms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BookIssuedServiceImpl implements BookIssuedService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Override
    public List<IssuedBookDto> issueBooks(List<IssuedBookDto> issuedBookDtoList, Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            if (employee.get().getRole().getValue().equalsIgnoreCase(LibraryConstants.LIBRARIAN) ||
                    employee.get().getRole().getValue().equalsIgnoreCase(LibraryConstants.ISSUER)) {
                List<IssuedBook> issuedBookList = convertToIssueBookEntities(issuedBookDtoList, employee.get());
                List<IssuedBook> issuedBooks = issuedBookRepository.saveAll(issuedBookList);
                return convertToDto(issuedBooks);
            }

            throw new EmployeeRoleNotSatisfied("Invalid role");
        }

        throw new EmployeeNotFoundException("Invalid user");

    }

    @Override
    public List<IssuedBookDto> searchRecords(Set<Long> recordIds, Set<Long> bookIds, Set<Long> studentIds,
                                             Set<Long> issuerIds, Set<Long> receiverIds) {
        List<IssuedBook> issuedBooks = issuedBookRepository.searchEmployee(getRecordIds(recordIds), getBookIds(bookIds),
                getStudentIds(studentIds), getIssuerIds(issuerIds), getReceiverIds(receiverIds));

        return convertToDto(issuedBooks);
    }

    @Override
    public List<IssuedBookDto> returnBooks(Set<IssuedBookDto> issuedBookDtos, Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.get().getRole().getValue().equalsIgnoreCase(LibraryConstants.LIBRARIAN)
                || employee.get().getRole().getValue().equalsIgnoreCase(LibraryConstants.RECEIVER)) {
            List<IssuedBook> issuedBooks = new ArrayList<>();

            for (IssuedBookDto issuedBookDto : issuedBookDtos) {
                Optional<IssuedBook> issuedBook = issuedBookRepository.findById(issuedBookDto.getId());

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                if (issuedBook.isPresent()) {
                    if(!issuedBook.get().isReturned()){
                        issuedBook.get().setReceiver(employee.get());
                        issuedBook.get().setReturnedDate(timestamp);
                        issuedBook.get().setReturned(true);
                        issuedBook.get().setTotalFine(issuedBookDto.getTotalFine());
                        issuedBook.get().setFinePaid(issuedBookDto.isFinePaid());
                        issuedBook.get().setComment(issuedBook.get().getComment()
                                + " " + issuedBookDto.getComment());

                        issuedBooks.add(issuedBook.get());
                    }
                }
            }

            List<IssuedBook> issuedBooks1 = issuedBookRepository.saveAll(issuedBooks);

            return convertToDto(issuedBooks1);
        }

        throw new EmployeeRoleNotSatisfied("invalid role of employee");

    }

    private Set<Long> getRecordIds(Set<Long> recordIds) {
        if (Objects.isNull(recordIds) || recordIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return recordIds;
    }

    private Set<Long> getStudentIds(Set<Long> studentIds) {
        if (Objects.isNull(studentIds) || studentIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return studentIds;
    }

    private Set<Long> getBookIds(Set<Long> bookIds) {
        if (Objects.isNull(bookIds) || bookIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return bookIds;
    }

    private Set<Long> getIssuerIds(Set<Long> issuerIds) {
        if (Objects.isNull(issuerIds) || issuerIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return issuerIds;
    }

    private Set<Long> getReceiverIds(Set<Long> receiverIds) {
        if (Objects.isNull(receiverIds) || receiverIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return receiverIds;
    }

    private Set<String> getStudentTypes(Set<String> studentTypes) {
        if (Objects.isNull(studentTypes) || studentTypes.isEmpty()) {
            return Collections.singleton("null");
        }
        return studentTypes;
    }

    private List<IssuedBook> convertToIssueBookEntities(List<IssuedBookDto> issuedBookDtoList, Employee employee) {
        List<IssuedBook> issuedBooks = new ArrayList<>();

        for (IssuedBookDto issuedBookDto : issuedBookDtoList) {
            Optional<Book> book = bookRepository.findById(issuedBookDto.getBookId());
            Optional<Student> student = studentRepository.findById(issuedBookDto.getStudentId());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if (book.isPresent() && student.isPresent()) {
                if (!book.get().getIsIssued()) {
                    IssuedBook issuedBook = IssuedBook.builder()
                            .book(book.get())
                            .student(student.get())
                            .issuer(employee)
                            .comment(issuedBookDto.getComment())
                            .dueDate(issuedBookDto.getDueDate())
                            .issuedDate(timestamp)
                            .totalRent(issuedBookDto.getTotalRent())
                            .build();

                    issuedBooks.add(issuedBook);
                }
            }
        }

        return issuedBooks;
    }

    private List<IssuedBookDto> convertToDto(List<IssuedBook> issuedBookList) {
        List<IssuedBookDto> issuedBookDtoList = new ArrayList<>();

        for (IssuedBook issuedBook : issuedBookList) {
            IssuedBookDto issuedBookDto = IssuedBookDto.builder()
                    .bookId(issuedBook.getBook().getId())
                    .comment(issuedBook.getComment())
                    .dueDate(issuedBook.getDueDate())
                    .id(issuedBook.getId())
                    .issuedDate(issuedBook.getIssuedDate())
                    .returnedDate(issuedBook.getReturnedDate())
                    .build();

            issuedBookDtoList.add(issuedBookDto);
        }
        return issuedBookDtoList;
    }
}
