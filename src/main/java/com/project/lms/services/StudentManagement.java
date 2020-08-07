package com.project.lms.services;

import com.project.lms.entities.dto.StudentDto;

import java.util.List;
import java.util.Set;

public interface StudentManagement {
    List<StudentDto> addStudents(List<StudentDto> studentDtoList, Long employeeId);

    List<StudentDto> searchStudents(Set<Long> studentIds, Set<String> studentTypes, Set<String> names, Set<Long> phoneNumbers);

    List<StudentDto> updateStudents(List<StudentDto> studentDtoList, Long employeeId);
}
