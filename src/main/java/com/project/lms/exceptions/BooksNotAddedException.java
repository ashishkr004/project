package com.project.lms.exceptions;

public class BooksNotAddedException extends RuntimeException {
    public BooksNotAddedException(String error) {
        super(error);
    }
}
