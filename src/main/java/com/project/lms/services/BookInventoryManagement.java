package com.project.lms.services;

import com.project.lms.entities.dto.BookDto;

import java.util.List;
import java.util.Set;

public interface BookInventoryManagement {
    List<BookDto> addBooks(List<BookDto> bookDtoList, Long employeeId);

    List<BookDto> searchBooks(Set<Long> ids, Set<String> titles, Set<String> subjects,
                              Set<String> authors, Set<Boolean> isIssued);

    List<BookDto> updateBooks(List<BookDto> bookDtoList, Long employeeId);
}
