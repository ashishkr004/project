package com.project.lms.controllers;

import com.project.lms.entities.dao.Book;
import com.project.lms.entities.dto.IssuedBookDto;
import com.project.lms.entities.dto.ResponseDto;
import com.project.lms.services.BookIssuedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/library")
public class LibraryController {


    @Autowired
    private BookIssuedService bookIssuedService;

    @PostMapping
    public ResponseEntity<ResponseDto> addRecords(@RequestBody List<IssuedBookDto> issuedBookDtoList) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Records added successfully",
                        bookIssuedService.addRecords(issuedBookDtoList)));
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
    public ResponseEntity<ResponseDto> updateRecords(@RequestParam(value = "ids", required = false) Set<Long> ids,
                                                     @RequestParam(value = "books", required = false) Set<Book> books) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Records updated successfully",
                        bookIssuedService.updateRecords(ids, books)));
    }
}
