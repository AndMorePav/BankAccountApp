package com.bank.reckoning.repository;

import com.bank.reckoning.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for work with accounts table.
 */
public interface AccountRepository  extends JpaRepository<Account, Long> {

    /**
     * Method for saving user.
     *
     * @param userId user id
     * @return integer of modified fields
     */
    @Modifying
    @Query(value = "insert into accounts(user_id) values (:userId)", nativeQuery = true)
    Integer saveByUserId (Long userId);

    /**
     * Method for finding accounts by user id
     *
     * @param userId user Id
     * @return list of accounts
     */
    List<Account> findByUserId(Long userId);
}
