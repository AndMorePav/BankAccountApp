package reckoning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reckoning.domain.User;

import java.util.Optional;

/**
 * Repository for work with users table.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
