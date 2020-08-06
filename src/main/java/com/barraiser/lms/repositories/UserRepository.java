package com.barraiser.lms.repositories;

import com.barraiser.lms.entities.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
