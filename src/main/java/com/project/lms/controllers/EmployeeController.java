package com.project.lms.controllers;

import com.project.lms.entities.dto.ResponseDto;
import com.project.lms.entities.dto.request.EmployeesRequestDto;
import com.project.lms.exceptions.InvalidRequestException;
import com.project.lms.services.EmployeeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/employees")
public class EmployeeController {
    @Autowired
    private EmployeeManagement employeeManagement;

    @PostMapping
    public ResponseEntity<ResponseDto> addEmployees(@RequestBody EmployeesRequestDto employeesRequestDto) {

        if(employeesRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Employees added successfully",
                        employeeManagement.addEmployees(employeesRequestDto.getEmployeesList(), employeesRequestDto.getEmployeeId())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> searchEmployees(@RequestParam(value = "ids", required = false) Set<Long> employeeIds,
                                                    @RequestParam(value = "employeeTypes", required = false) Set<String> employeeTypes,
                                                    @RequestParam(value = "names", required = false) Set<String> names,
                                                    @RequestParam(value = "phoneNumbers", required = false) Set<Long> phoneNumbers) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Employees search successfully",
                        employeeManagement.searchEmployees(employeeIds, employeeTypes, names, phoneNumbers)));
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> updateEmployees(@RequestParam(value = "ids", required = false) Set<Long> employeeIds,
                                                      @RequestParam(value = "phoneNumbers", required = false) Set<Long> phoneNumbers) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Employees deleted successfully",
                        employeeManagement.deleteEmployees(employeeIds, phoneNumbers)));
    }
}
