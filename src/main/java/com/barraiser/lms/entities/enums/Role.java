package com.barraiser.lms.entities.enums;

public enum Role {
    ADMIN("ADMIN"),     //can issue and receive the books
    BASIC("BASIC");     //only receieve the books

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
