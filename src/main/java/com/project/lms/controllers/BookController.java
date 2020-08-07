package com.project.lms.controllers;

import com.project.lms.entities.dto.BookDto;
import com.project.lms.entities.dto.ResponseDto;
import com.project.lms.entities.dto.request.BooksRequestDto;
import com.project.lms.services.BookInventoryManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/project/v1.0/books")
public class BookController {

    @Autowired
    private BookInventoryManagement bookInventoryManagement;

    @PostMapping
    public ResponseEntity<ResponseDto> addBooks(@RequestBody BooksRequestDto booksRequestDto) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Books added successfully",
                        bookInventoryManagement.addBooks(booksRequestDto.getBooksList())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> readMetadata(@RequestParam(value = "ids", required = false) Set<Long> ids,
                                                    @RequestParam(value = "titles", required = false) Set<String> titles,
                                                    @RequestParam(value = "subjects", required = false) Set<String> subjects,
                                                    @RequestParam(value = "authors", required = false) Set<String> authors,
                                                    @RequestParam(value = "isIssued", required = false) Set<Boolean> isIssued) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Books search successfully",
                        bookInventoryManagement.searchBooks(ids, titles, subjects, authors, isIssued)));
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> deleteMetadata(@RequestParam(value = "ids", required = false) Set<Long> ids) {
        return ResponseEntity.ok(
                new ResponseDto("200", "Books deleted successfully",
                        bookInventoryManagement.deleteBooks(ids)));
    }
}
