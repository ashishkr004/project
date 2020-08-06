package com.barraiser.lms.services;

import com.barraiser.lms.entities.dto.BookDto;

import java.util.List;
import java.util.Set;

public interface BookInventoryManagement {
    List<BookDto> addBooks(List<BookDto> bookDtoList);

    List<BookDto> searchBooks(Set<Long> ids, Set<String> titles, Set<String> subjects,
                       Set<String> authors, Set<Boolean> isIssued);

    Boolean deleteBooks(Set<Long> ids);
}
