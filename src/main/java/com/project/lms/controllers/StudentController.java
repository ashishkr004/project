package com.project.lms.controllers;

import com.project.lms.entities.dto.ResponseDto;
import com.project.lms.entities.dto.StudentDto;
import com.project.lms.entities.dto.request.StudentRequestDto;
import com.project.lms.exceptions.InvalidRequestException;
import com.project.lms.services.StudentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/students")
public class StudentController {
    @Autowired
    private StudentManagement studentManagement;

    @PostMapping
    public ResponseEntity<ResponseDto> addStudents(@RequestBody StudentRequestDto studentRequestDto) {

        if(studentRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Students added successfully",
                        studentManagement.addStudents(studentRequestDto.getStudentsList(), studentRequestDto.getEmployeeId())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> searchStudents(@RequestParam(value = "ids", required = false) Set<Long> studentIds,
                                                    @RequestParam(value = "studentTypes", required = false) Set<String> studentTypes,
                                                    @RequestParam(value = "names", required = false) Set<String> names,
                                                    @RequestParam(value = "phoneNumbers", required = false) Set<Long> phoneNumbers) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Students search successfully",
                        studentManagement.searchStudents(studentIds, studentTypes, names, phoneNumbers)));
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> updateStudents(@RequestParam(value = "ids", required = false) Set<Long> ids,
                                                      @RequestParam(value = "phoneNumbers", required = false) Set<Long> phoneNumbers) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Books deleted successfully",
                        studentManagement.updateStudents(ids, phoneNumbers)));
    }
}
