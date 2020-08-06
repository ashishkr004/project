package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;
import com.barraiser.lms.entities.dto.EmployeeDto;

import java.util.List;
import java.util.Set;

public interface EmployeeManagement {

    List<BookDto> addEmployees(List<EmployeeDto> employeeDtoList);

    List<BookDto> searchEmployees(Set<Long> userIds, Set<Long> employeeIds, Set<String> names, Set<Long> phoneNumber);

    Boolean deleteEmployees(Set<Long> userIds, Set<Long> employeeIds);
}
