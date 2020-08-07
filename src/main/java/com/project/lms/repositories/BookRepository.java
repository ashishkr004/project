package com.project.lms.repositories;

import com.project.lms.entities.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
            value = "SELECT * FROM book WHERE " +
                    " (-1000 IN (:ids) OR id IN (:ids)) AND " +
                    " ('null' IN (:titles) OR title IN (:titles)) AND " +
                    " ('null' IN (:subjects) OR subject IN (:subjects)) AND " +
                    " ('null' IN (:authors) OR author IN (:authors)) AND " +
                    " (false IN (:isIssued) OR is_issued IN (:isIssued)) AND " +
                    " is_active = true",
            nativeQuery = true
    )
    List<Book> searchBooks(@Param("ids") Set<Long> ids, @Param("titles") Set<String> titles,
                           @Param("subjects") Set<String> subjects, @Param("authors") Set<String> authors,
                           @Param("isIssued") Set<Boolean> isIssued);
}
