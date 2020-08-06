package com.barraiser.lms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String password;
    private String name;
    private String address;
    private Long phoneNumber;
}
