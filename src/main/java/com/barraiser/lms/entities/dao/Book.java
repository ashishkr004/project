package com.barraiser.lms.entities.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "book")
public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "title", nullable = false)
    private String title;

//    @Column(name = "subject", nullable = false)
    private String subject;

//    @Column(name = "author", nullable = false)
    private String author;

//    @Column(name = "isIssued", nullable = false)
    private boolean isIssued;
}
