package com.barraiser.lms.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    protected String password;
    protected String name;
    protected String address;
    protected Long phoneNumber;
}
