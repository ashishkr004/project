package com.project.lms.exceptions;

public class EmployeeNotAddedException extends RuntimeException {
    public EmployeeNotAddedException(String error) {
        super(error);
    }
}
