package com.project.lms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String employeeType;
    private String password;
    private String name;
    private String address;
    private Long phoneNumber;
    private String role;
    private Boolean isActive;
}
