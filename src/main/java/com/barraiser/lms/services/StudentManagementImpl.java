package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;
import com.barraiser.lms.entities.dto.EmployeeDto;
import com.barraiser.lms.entities.dto.StudentDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StudentManagementImpl implements StudentManagement {

    @Override
    public List<StudentDto> addStudents(List<StudentDto> studentDtoList) {
        return null;
    };


    @Override
    public List<StudentDto> searchStudents(Set<Long> userIds, Set<Long> studentIds, Set<String> names, Set<Long> phoneNumber){
        return null;
    };

    @Override
    public Boolean deleteStudents(Set<Long> userIds, Set<Long> studentIds){
        return null;
    };

    private Set<Long> userIds(Set<Long> userIds) {
        if(Objects.isNull(userIds) || userIds.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return userIds;
    }

    private Set<Long> getStudentIds(Set<Long> studentIds) {
        if(Objects.isNull(studentIds) || studentIds.isEmpty()){
            return Collections.singleton(-1000L);
        }
        return studentIds;
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
