package com.project.lms.services;

import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dto.EmployeeDto;
import com.project.lms.exceptions.EmployeeNotAddedException;
import com.project.lms.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeManagementImpl implements EmployeeManagement {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> addEmployees(List<EmployeeDto> employeeDtoList) {
        try {
            List<Employee> employeeList = employeeRepository.saveAll(convertToListOfEmployee(employeeDtoList));

            return convertToListOfEmployeeDto(employeeList);
        } catch (Exception e) {
            throw new EmployeeNotAddedException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeDto> searchEmployees(Set<Long> employeeIds, Set<String> employeeTypes,
                                             Set<String> names, Set<Long> phoneNumbers) {

        List<Employee> employeeList = employeeRepository.searchEmployee(getEmployeeIds(employeeIds), getEmployeeTypes(employeeTypes),
                getNames(names), getPhoneNumbers(phoneNumbers));

        return convertToListOfEmployeeDto(employeeList);
    }

    @Override
    public Boolean deleteEmployees(Set<Long> employeeIds, Set<Long> phoneNumbers) {
        List<EmployeeDto> employeeDtoList = searchEmployees(employeeIds, null, null, phoneNumbers);

        List<Employee> employeeList = convertToListOfEmployee(employeeDtoList);

        for (Employee employee : employeeList) {
            employee.setIsActive(false);
        }

        employeeRepository.saveAll(employeeList);

        return true;
    }

    private Set<Long> getEmployeeIds(Set<Long> employeeIds) {
        if (Objects.isNull(employeeIds) || employeeIds.isEmpty()) {
            return Collections.singleton(-1000L);
        }
        return employeeIds;
    }

    private Set<String> getEmployeeTypes(Set<String> employeeTypes) {
        if (Objects.isNull(employeeTypes) || employeeTypes.isEmpty()) {
            return Collections.singleton("null");
        }
        return employeeTypes;
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

    private List<Employee> convertToListOfEmployee(List<EmployeeDto> employeeDtoList) {

        List<Employee> employeeList = new ArrayList<>();

        for (EmployeeDto employeeDto : employeeDtoList) {
            Employee employee = Employee.builder()
                    .address(employeeDto.getAddress())
                    .employeeType(employeeDto.getEmployeeType())
                    .id(employeeDto.getId())
                    .name(employeeDto.getName())
                    .password(employeeDto.getPassword())
//                    .role(Role.values(employeeDto.getRole())
                    .phoneNumber(employeeDto.getPhoneNumber())
                    .build();

            employeeList.add(employee);
        }

        return employeeList;
    }

    private List<EmployeeDto> convertToListOfEmployeeDto(List<Employee> employeeList) {

        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .address(employee.getAddress())
                    .employeeType(employee.getEmployeeType())
                    .id(employee.getId())
                    .name(employee.getName())
                    .password(employee.getPassword())
//                    .role(Role.values(employeeDto.getRole())
                    .phoneNumber(employee.getPhoneNumber())
                    .build();

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }
}
