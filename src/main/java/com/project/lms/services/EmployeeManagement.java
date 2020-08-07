package com.project.lms.services;

import com.project.lms.entities.dto.EmployeeDto;

import java.util.List;
import java.util.Set;

public interface EmployeeManagement {

    List<EmployeeDto> addEmployees(List<EmployeeDto> employeeDtoList);

    List<EmployeeDto> searchEmployees(Set<Long> employeeIds, Set<String> employeeTypes, Set<String> names, Set<Long> phoneNumbers);

    Boolean deleteEmployees(Set<Long> employeeIds, Set<Long> phoneNumbers);
}
