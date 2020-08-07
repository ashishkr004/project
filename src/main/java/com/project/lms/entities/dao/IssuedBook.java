package com.project.lms.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "issued_book")
public class IssuedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="issuer_id")
    private Employee issuer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="receiver_id")
    private Employee receiver;

    @Column(name = "issued_date", nullable = false)
    private Timestamp issuedDate;

    @Column(name = "due_date", nullable = false)
    private Timestamp dueDate;

    @Column(name = "date_returned", nullable = false)
    private Timestamp returnedDate;

    @Column(name = "total_rent", nullable = false)
    private Double totalRent;

    @Column(name = "total_fine", nullable = false)
    private Double totalFine;

    @Column(name = "is_returned", nullable = false)
    private boolean isReturned;

    @Column(name = "fine_paid", nullable = false)
    private boolean finePaid;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_active")
    private Boolean isActive;
}
