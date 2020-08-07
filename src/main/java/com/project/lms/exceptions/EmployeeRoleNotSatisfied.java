package com.project.lms.exceptions;

public class EmployeeRoleNotSatisfied extends RuntimeException {
    public EmployeeRoleNotSatisfied(String error) {
        super(error);
    }
}
