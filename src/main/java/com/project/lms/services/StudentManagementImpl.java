package com.project.lms.services;

import com.project.lms.constants.LibraryConstants;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dao.Student;
import com.project.lms.entities.dto.StudentDto;
import com.project.lms.exceptions.EmployeeNotAddedException;
import com.project.lms.exceptions.EmployeeRoleNotSatisfied;
import com.project.lms.repositories.EmployeeRepository;
import com.project.lms.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentManagementImpl implements StudentManagement {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<StudentDto> addStudents(List<StudentDto> studentDtoList, Long employeeId) {
        try {

            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if(employee.isPresent() && employee.get().getRole().equalsIgnoreCase(LibraryConstants.ADMIN)) {
                List<Student> studentList = studentRepository.saveAll(convertToListOfStudents(studentDtoList));

                return convertToListOfStudentDto(studentList);
            }

            throw new EmployeeRoleNotSatisfied("Not an valid employee");

        } catch (Exception e) {
            throw new EmployeeNotAddedException(e.getMessage());
        }
    }


    @Override
    public List<StudentDto> searchStudents(Set<Long> studentIds, Set<String> studentTypes, Set<String> names, Set<Long> phoneNumber) {
        List<Student> studentList = studentRepository.searchEmployee(getStudentIds(studentIds), getStudentTypes(studentTypes),
                getNames(names), getPhoneNumbers(phoneNumber));

        return convertToListOfStudentDto(studentList);
    }

    @Override
    public Boolean deleteStudents(Set<Long> studentIds, Set<Long> phoneNumbers) {

        List<StudentDto> studentDtoList = searchStudents(studentIds, null, null, phoneNumbers);

        List<Student> studentList = convertToListOfStudents(studentDtoList);

        for (Student student : studentList) {
            student.setIsActive(false);
        }

        studentRepository.saveAll(studentList);

        return true;
    }


    private Set<Long> getStudentIds(Set<Long> studentIds) {
        if (Objects.isNull(studentIds) || studentIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return studentIds;
    }

    private Set<String> getStudentTypes(Set<String> studentTypes) {
        if (Objects.isNull(studentTypes) || studentTypes.isEmpty()) {
            return Collections.singleton("null");
        }
        return studentTypes;
    }

    private Set<String> getNames(Set<String> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
            return Collections.singleton("null");
        }
        return names;
    }

    private Set<Long> getPhoneNumbers(Set<Long> phoneNumbers) {
        if (Objects.isNull(phoneNumbers) || phoneNumbers.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return phoneNumbers;
    }

    private List<Student> convertToListOfStudents(List<StudentDto> studentDtoList) {

        List<Student> studentList = new ArrayList<>();

        for (StudentDto studentDto : studentDtoList) {
            Student student = Student.builder()
                    .address(studentDto.getAddress())
                    .studentType(studentDto.getStudentType())
                    .id(studentDto.getId())
                    .name(studentDto.getName())
                    .password(studentDto.getPassword())
                    .phoneNumber(studentDto.getPhoneNumber())
                    .isActive(studentDto.getIsActive())
                    .build();

            studentList.add(student);
        }

        return studentList;
    }

    private List<StudentDto> convertToListOfStudentDto(List<Student> studentList) {

        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : studentList) {
            StudentDto studentDto = StudentDto.builder()
                    .address(student.getAddress())
                    .studentType(student.getStudentType())
                    .id(student.getId())
                    .name(student.getName())
                    .password(student.getPassword())
                    .phoneNumber(student.getPhoneNumber())
                    .build();

            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }
}
