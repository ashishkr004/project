package com.project.lms.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String error) {
        super(error);
    }
}
