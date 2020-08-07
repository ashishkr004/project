package com.project.lms.controllers;

import com.project.lms.entities.dto.ResponseDto;
import com.project.lms.entities.dto.request.IssueBooksRequestDto;
import com.project.lms.exceptions.InvalidRequestException;
import com.project.lms.services.BookIssuedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/library")
public class LibraryController {


    @Autowired
    private BookIssuedService bookIssuedService;

    @PostMapping
    public ResponseEntity<ResponseDto> addRecords(@RequestBody IssueBooksRequestDto issueBooksRequestDto) {


        if(issueBooksRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Records added successfully",
                        bookIssuedService.issueBooks(issueBooksRequestDto.getIssuedBooks(), issueBooksRequestDto.getEmployeeId())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> searchRecords(@RequestParam(value = "ids", required = false) Set<Long> ids,
                                                     @RequestParam(value = "bookIds", required = false) Set<Long> bookIds,
                                                     @RequestParam(value = "studentIds", required = false) Set<Long> studentIds,
                                                     @RequestParam(value = "issuerIds", required = false) Set<Long> issuerIds,
                                                     @RequestParam(value = "receiverIds", required = false)Set<Long> receiverIds) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Records search successfully",
                        bookIssuedService.searchRecords(ids, bookIds, studentIds, issuerIds, receiverIds)));
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> updateRecords(@RequestBody IssueBooksRequestDto issueBooksRequestDto) {

        if(issueBooksRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Records updated successfully",
                        bookIssuedService.returnBooks(issueBooksRequestDto.getIssuedBooks(), issueBooksRequestDto.getEmployeeId())));
    }
}
