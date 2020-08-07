package com.project.lms.controllers;

import com.project.lms.entities.dto.response.ResponseDto;
import com.project.lms.entities.dto.request.BooksRequestDto;
import com.project.lms.exceptions.InvalidRequestException;
import com.project.lms.services.BookInventoryManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/books")
public class BookController {

    @Autowired
    private BookInventoryManagement bookInventoryManagement;

    @PostMapping
    public ResponseEntity<ResponseDto> addBooks(@RequestBody BooksRequestDto booksRequestDto) {

        if (booksRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Books added successfully",
                        bookInventoryManagement.addBooks(booksRequestDto.getBooksList(), booksRequestDto.getEmployeeId())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> searchBooks(@RequestParam(value = "ids", required = false) Set<Long> ids,
                                                   @RequestParam(value = "titles", required = false) Set<String> titles,
                                                   @RequestParam(value = "subjects", required = false) Set<String> subjects,
                                                   @RequestParam(value = "authors", required = false) Set<String> authors,
                                                   @RequestParam(value = "isIssued", required = false) Set<Boolean> isIssued) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Books search successfully",
                        bookInventoryManagement.searchBooks(ids, titles, subjects, authors, isIssued)));
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> updateBooks(@RequestBody BooksRequestDto booksRequestDto) {

        if (booksRequestDto.getEmployeeId() == null) {
            throw new InvalidRequestException("EmployeeId cannot be null");
        }

        return ResponseEntity.ok(
                new ResponseDto("200", "Books updated successfully",
                        bookInventoryManagement.updateBooks(booksRequestDto.getBooksList(), booksRequestDto.getEmployeeId())));
    }
}
