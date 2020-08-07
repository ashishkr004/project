package com.project.lms.repositories;

import com.project.lms.entities.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            value = "SELECT * FROM student WHERE " +
                    " (-1000 IN (:ids) OR id IN (:ids)) AND " +
                    " ('null' IN (:studentTypes) OR studentType IN (:studentTypes)) AND " +
                    " ('null' IN (:names) OR name IN (:names)) AND " +
                    " (-1000 IN (:phoneNumbers) OR phoneNumber IN (:phoneNumbers)) AND " +
                    " active = true",
            nativeQuery = true
    )
    List<Student> searchEmployee(@Param("ids") Set<Long> ids, @Param("studentTypes") Set<String> studentTypes,
                                 @Param("names") Set<String> names, @Param("phoneNumbers") Set<Long> phoneNumbers);
}
