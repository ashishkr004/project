package com.project.lms.controllers;


import com.project.lms.entities.dto.response.ApiResponse;
import com.project.lms.exceptions.EmployeeNotFoundException;
import com.project.lms.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ApiIgnore
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleInvalidRequestException(HttpServletRequest req, InvalidRequestException ex) {
        return ApiResponse.of(601, ex.getMessage(), null);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleEmployeeNotFoundException(HttpServletRequest req, EmployeeNotFoundException ex) {
        return ApiResponse.of(601, ex.getMessage(), null);
    }
}
