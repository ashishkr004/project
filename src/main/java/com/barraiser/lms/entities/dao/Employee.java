package com.barraiser.lms.entities.dao;

import com.barraiser.lms.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "book")
public class Employee extends User {
//    @Column(name = "employeeId", nullable = false)
    private Long employeeId;

    private String employeeType;

    private Role role;
}
