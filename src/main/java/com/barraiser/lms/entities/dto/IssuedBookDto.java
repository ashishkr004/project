package com.barraiser.lms.entities.dto;

import com.barraiser.lms.entities.dao.Book;
import com.barraiser.lms.entities.dao.Employee;
import com.barraiser.lms.entities.dao.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBookDto {
    private Long id;
    private Book book;
    private Student student;
    private Employee issuer;
    private Employee receiver;
    private Timestamp issuedDate;
    private Timestamp dueDate;
    private Timestamp returnedDate;
    private Double totalRent;
    private Double totalFine;
    private boolean isReturned;
    private boolean finePaid;
    private String comment;
}
