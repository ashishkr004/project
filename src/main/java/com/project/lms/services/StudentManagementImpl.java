package com.project.lms.services;

import com.project.lms.constants.LibraryConstants;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dao.Student;
import com.project.lms.entities.dto.EmployeeDto;
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
    public List<StudentDto> updateStudents(List<StudentDto> studentDtoList, Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent() && employee.get().getRole().equalsIgnoreCase(LibraryConstants.ADMIN)) {

            Set<Long> ids = new HashSet<>();

            HashMap<Long, StudentDto> studentDtoHashMap = new HashMap<>();

            for (StudentDto studentDto : studentDtoList) {
                ids.add(studentDto.getId());
                studentDtoHashMap.put(studentDto.getId(), studentDto);
            }

            List<Student> students = studentRepository.findAllById(ids);
            students = updateFetchedEmployees(students, studentDtoHashMap);

            List<Student> updatedStudents = studentRepository.saveAll(students);

            return convertToListOfStudentDto(updatedStudents);
        }

        throw new EmployeeRoleNotSatisfied("Not an valid employee");
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
                    .isActive(student.getIsActive())
                    .build();

            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }

    private List<Student> updateFetchedEmployees(List<Student> students, HashMap<Long, StudentDto> studentDtoHashMap) {
        for (Student student : students) {
            StudentDto studentDto = studentDtoHashMap.get(student.getId());

            student.setAddress(studentDto.getAddress() == null ? student.getAddress() : studentDto.getAddress());
            student.setIsActive(studentDto.getIsActive() == null ? student.getIsActive() : studentDto.getIsActive());
            student.setStudentType(studentDto.getStudentType() == null ? student.getStudentType() : studentDto.getStudentType());
            student.setName(studentDto.getName() == null ? student.getName() : studentDto.getName());
            student.setPassword(studentDto.getPassword() == null ? student.getPassword() : studentDto.getPassword());
            student.setPhoneNumber(studentDto.getPhoneNumber() == null ? student.getPhoneNumber() : studentDto.getPhoneNumber());
        }

        return students;
    }
}
