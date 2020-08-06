package com.barraiser.lms.repositories;

import com.barraiser.lms.entities.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
