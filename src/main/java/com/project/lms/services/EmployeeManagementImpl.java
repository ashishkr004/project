package com.project.lms.services;

import com.project.lms.constants.LibraryConstants;
import com.project.lms.entities.dao.Employee;
import com.project.lms.entities.dto.EmployeeDto;
import com.project.lms.exceptions.EmployeeNotAddedException;
import com.project.lms.exceptions.EmployeeRoleNotSatisfied;
import com.project.lms.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeManagementImpl implements EmployeeManagement {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> addEmployees(List<EmployeeDto> employeeDtoList, Long employeeId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if(employee.isPresent() && employee.get().getRole().equalsIgnoreCase(LibraryConstants.ADMIN)) {
                List<Employee> employeeList = employeeRepository.saveAll(convertToListOfEmployee(employeeDtoList));

                return convertToListOfEmployeeDto(employeeList);
            }

            throw new EmployeeRoleNotSatisfied("Not an valid employee");
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
    public List<EmployeeDto> updateEmployees(List<EmployeeDto> employeeDtoList, Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent() && employee.get().getRole().equalsIgnoreCase(LibraryConstants.ADMIN)) {

            Set<Long> ids = new HashSet<>();

            HashMap<Long, EmployeeDto> employeeDtoHashMap = new HashMap<>();

            for (EmployeeDto employeeDto : employeeDtoList) {
                ids.add(employeeDto.getId());
                employeeDtoHashMap.put(employeeDto.getId(), employeeDto);
            }

            List<Employee> employees = employeeRepository.findAllById(ids);
            employees = updateFetchedEmployees(employees, employeeDtoHashMap);

            List<Employee> updatedEmployees = employeeRepository.saveAll(employees);

            return convertToListOfEmployeeDto(updatedEmployees);
        }

        throw new EmployeeRoleNotSatisfied("Not an valid employee");
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
                    .role(employeeDto.getRole())
                    .phoneNumber(employeeDto.getPhoneNumber())
                    .isActive(employeeDto.getIsActive() == null ? true : employeeDto.getIsActive())
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
                    .role(employee.getRole())
                    .phoneNumber(employee.getPhoneNumber())
                    .isActive(employee.getIsActive())
                    .build();

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }

    private List<Employee> updateFetchedEmployees(List<Employee> employees, HashMap<Long, EmployeeDto> employeeDtoHashMap) {
        for (Employee employee1 : employees) {
            EmployeeDto employeeDto = employeeDtoHashMap.get(employee1.getId());

            employee1.setAddress(employeeDto.getAddress() == null ? employee1.getAddress() : employeeDto.getAddress());
            employee1.setIsActive(employeeDto.getIsActive() == null ? employee1.getIsActive() : employeeDto.getIsActive());
            employee1.setEmployeeType(employeeDto.getEmployeeType() == null ? employee1.getEmployeeType() : employeeDto.getEmployeeType());
            employee1.setName(employeeDto.getName() == null ? employee1.getName() : employeeDto.getName());
            employee1.setPassword(employeeDto.getPassword() == null ? employee1.getPassword() : employeeDto.getPassword());
            employee1.setRole(employeeDto.getRole() == null ? employee1.getRole() : employeeDto.getRole());
            employee1.setPhoneNumber(employeeDto.getPhoneNumber() == null ? employee1.getPhoneNumber() : employeeDto.getPhoneNumber());
        }

        return employees;
    }
}
