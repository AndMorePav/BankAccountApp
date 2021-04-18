package com.bank.reckoning.repository;

import com.bank.reckoning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for work with users table.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
