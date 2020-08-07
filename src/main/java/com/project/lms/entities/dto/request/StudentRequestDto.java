package com.project.lms.entities.dto.request;


import com.project.lms.entities.dao.Student;
import com.project.lms.entities.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    List<StudentDto> studentsList;
    Long employeeId;
}
