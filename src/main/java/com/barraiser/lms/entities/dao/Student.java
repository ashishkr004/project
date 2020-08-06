package com.barraiser.lms.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "book")
public class Student extends User {
    private Long studentId;

    private String studentType;
}
