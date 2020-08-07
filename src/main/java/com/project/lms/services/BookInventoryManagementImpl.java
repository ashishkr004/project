package com.project.lms.services;

import com.project.lms.constants.LibraryConstants;
import com.project.lms.entities.dao.Book;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dto.BookDto;
import com.project.lms.exceptions.BooksNotAddedException;
import com.project.lms.exceptions.EmployeeRoleNotSatisfied;
import com.project.lms.repositories.BookRepository;
import com.project.lms.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookInventoryManagementImpl implements BookInventoryManagement {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<BookDto> addBooks(List<BookDto> bookDtoList, Long employeeId) {

        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if(employee.isPresent() && employee.get().getRole().equalsIgnoreCase(LibraryConstants.LIBRARIAN)){
                List<Book> bookList = bookRepository.saveAll(convertToListOfBook(bookDtoList));

                return convertToListOfBookDto(bookList);
            }

            throw new EmployeeRoleNotSatisfied("Not an valid employee");

        } catch (Exception e) {
            throw new BooksNotAddedException(e.getMessage());
        }
    }

    @Override
    public List<BookDto> searchBooks(Set<Long> ids, Set<String> titles, Set<String> subjects,
                                     Set<String> authors, Set<Boolean> isIssued) {

        List<Book> books = bookRepository.searchBooks(getIds(ids), getTitles(titles), getSubjects(subjects),
                getAuthors(authors), getIsIssued(isIssued));

        return convertToListOfBookDto(books);
    }

    @Override
    public Boolean deleteBooks(Set<Long> ids) {
        List<BookDto> bookDtoList = searchBooks(ids, null, null, null, null);

        List<Book> books = convertToListOfBook(bookDtoList);

        for (Book book : books) {
            book.setIsActive(false);
        }

        bookRepository.saveAll(books);

        return true;
    }

    private Set<Long> getIds(Set<Long> ids) {
        if (Objects.isNull(ids) || ids.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return ids;
    }

    private Set<String> getTitles(Set<String> titles) {
        if (Objects.isNull(titles) || titles.isEmpty()) {
            return Collections.singleton("null");
        }
        return titles;
    }

    private Set<String> getSubjects(Set<String> subjects) {
        if (Objects.isNull(subjects) || subjects.isEmpty()) {
            return Collections.singleton("null");
        }
        return subjects;
    }

    private Set<String> getAuthors(Set<String> authors) {
        if (Objects.isNull(authors) || authors.isEmpty()) {
            return Collections.singleton("null");
        }
        return authors;
    }

    private Set<Boolean> getIsIssued(Set<Boolean> isIssued) {
        if (Objects.isNull(isIssued) || isIssued.isEmpty()) {
            return Collections.singleton(false);
        }
        return isIssued;
    }

    private List<Book> convertToListOfBook(List<BookDto> bookDtoList) {

        List<Book> bookList = new ArrayList<>();

        for (BookDto bookDto : bookDtoList) {
            Book book = Book.builder()
                    .id(bookDto.getId())
                    .author(bookDto.getAuthor())
                    .subject(bookDto.getSubject())
                    .title(bookDto.getTitle())
                    .isIssued(false)
                    .isActive(bookDto.getIsActive() == null ? true : bookDto.getIsActive())
                    .build();

            bookList.add(book);
        }

        return bookList;
    }

    private List<BookDto> convertToListOfBookDto(List<Book> bookList) {

        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : bookList) {
            BookDto bookDto = BookDto.builder()
                    .id(book.getId())
                    .author(book.getAuthor())
                    .subject(book.getSubject())
                    .title(book.getTitle())
                    .isIssued(book.getIsIssued())
                    .isActive(book.getIsActive())
                    .build();

            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }
}
