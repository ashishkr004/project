package com.barraiser.lms.repositories;

import com.barraiser.lms.entities.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
