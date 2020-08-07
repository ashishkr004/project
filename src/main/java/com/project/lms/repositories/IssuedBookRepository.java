package com.project.lms.repositories;

import com.project.lms.entities.dao.IssuedBook;
import com.project.lms.entities.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

    @Query(
            value = "SELECT * FROM issued_book WHERE " +
                    " (-1000 IN (:recordIds) OR id IN (:recordIds)) AND " +
                    " (-1000 IN (:bookIds) OR book_id IN (:bookIds)) AND " +
                    " (-1000 IN (:studentIds) OR student_id IN (:studentIds)) AND " +
                    " (-1000 IN (:issuerIds) OR issuer_id IN (:issuerIds)) AND " +
                    " (-1000 IN (:receiverIds) OR receiver_id IN (:receiverIds))",
            nativeQuery = true
    )
    List<IssuedBook> searchEmployee(@Param("recordIds") Set<Long> recordIds,
                                 @Param("bookIds") Set<Long> bookIds,
                                 @Param("studentIds") Set<Long> studentIds,
                                 @Param("issuerIds") Set<Long> issuerIds,
                                 @Param("receiverIds") Set<Long> receiverIds);
}
