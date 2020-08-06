package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;
import com.barraiser.lms.entities.dto.EmployeeDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EmployeeManagementImpl implements EmployeeManagement {
    @Override
    public List<BookDto> addEmployees(List<EmployeeDto> employeeDtoList){
        return null;
    };

    @Override
    public List<BookDto> searchEmployees(Set<Long> userIds, Set<Long> employeeIds, Set<String> names, Set<Long> phoneNumbers){
        return null;
    };

    @Override
    public Boolean deleteEmployees(Set<Long> userIds, Set<Long> employeeIds) {
        return null;
    };

    private Set<Long> userIds(Set<Long> userIds) {
        if(Objects.isNull(userIds) || userIds.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return userIds;
    }

    private Set<Long> getEmployeeIds(Set<Long> employeeIds) {
        if(Objects.isNull(employeeIds) || employeeIds.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return employeeIds;
    }

    private Set<String> getNames(Set<String> names) {
        if(Objects.isNull(names) || names.isEmpty()){
            return Collections.singleton("null");
        }
        return names;
    }

    private Set<Long> getPhoneNumbers(Set<Long> phoneNumbers) {
        if(Objects.isNull(phoneNumbers) || phoneNumbers.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return phoneNumbers;
    }
}
