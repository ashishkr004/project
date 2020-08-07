package com.project.lms.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String error) {
        super(error);
    }
}
