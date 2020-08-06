package com.barraiser.lms.entities.dto;

import com.barraiser.lms.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto extends UserDto {
    private Long employeeId;

    private String employeeType;

    private Role role;
}
