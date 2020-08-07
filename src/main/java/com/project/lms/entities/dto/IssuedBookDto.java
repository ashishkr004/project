package com.project.lms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBookDto {
    private Long id;
    private Long bookId;
    private Long studentId;
    private Long issuerId;
    private Long receiverId;
    private Timestamp issuedDate;
    private Timestamp dueDate;
    private Timestamp returnedDate;
    private Double totalRent;
    private Double totalFine;
    private Boolean isReturned;
    private Boolean finePaid;
    private String comment;
}
