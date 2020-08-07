package com.project.lms.repositories;

import com.project.lms.entities.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
            value = "SELECT * FROM employee WHERE " +
                    " (-1000 IN (:ids) OR id IN (:ids)) AND " +
                    " ('null' IN (:employeeTypes) OR employee_type IN (:employeeTypes)) AND " +
                    " ('null' IN (:names) OR name IN (:names)) AND " +
                    " (-1000 IN (:phoneNumbers) OR phone_number IN (:phoneNumbers)) AND " +
                    " is_active = true",
            nativeQuery = true
    )
    List<Employee> searchEmployee(@Param("ids") Set<Long> ids, @Param("employeeTypes") Set<String> employeeTypes,
                                  @Param("names") Set<String> names, @Param("phoneNumbers") Set<Long> phoneNumbers);
}
