package com.bank.reckoning.repository;

import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, Long> {
}
