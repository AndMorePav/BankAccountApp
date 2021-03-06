package com.bank.reckoning.repository;

import com.bank.reckoning.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for work with accounts table.
 */
public interface AccountRepository  extends JpaRepository<Account, Long> {

    /**
     * Method for finding accounts by user id
     *
     * @param userId user Id
     * @return list of accounts
     */
    List<Account> findByUserId(Long userId);
}
