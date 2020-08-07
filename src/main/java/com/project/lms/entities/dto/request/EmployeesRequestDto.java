package com.project.lms.entities.dto.request;

import com.project.lms.entities.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesRequestDto {
    List<EmployeeDto> employeesList;
    Long employeeId;
}
