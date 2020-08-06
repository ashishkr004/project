package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;
import com.barraiser.lms.entities.dto.EmployeeDto;
import com.barraiser.lms.entities.dto.StudentDto;

import java.util.List;
import java.util.Set;

public interface StudentManagement {
    List<StudentDto> addStudents(List<StudentDto> studentDtoList);

    List<StudentDto> searchStudents(Set<Long> userIds, Set<Long> studentIds, Set<String> names, Set<Long> phoneNumber);

    Boolean deleteStudents(Set<Long> userIds, Set<Long> studentIds);
}
