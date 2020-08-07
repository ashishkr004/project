package com.project.lms.entities.dto.request;

import com.project.lms.entities.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksRequestDto {
    List<BookDto> booksList;
}
