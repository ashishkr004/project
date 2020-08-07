package com.project.lms.entities.enums;

public enum Role {
    ADMIN("ADMIN"),     //can issue and receive the books
    LIBRARIAN("LIBRARIAN"),
    ISSUER("ISSUER"),   //only receieve the books
    RECEIVER("RECEIVER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getValue() {
        return this.role;
    }
}
