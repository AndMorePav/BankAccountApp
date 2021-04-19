package com.bank.reckoning.service;

import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.dto.AccountViewDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for work with accounts.
 */
public interface AccountService {

    /**
     * Method for creating account.
     *
     * @param accountCreateDto DTO for creating new account.
     * @return boolean operation success
     */
    Optional<AccountViewDto> createAccount(AccountCreateDto accountCreateDto);

    /**
     * Method for updating account.
     *
     * @param operationType    type of operation.
     * @param accountUpdateDto DTO for updating account.
     */
    void updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto);

    /**
     * Method for getting accounts by user id.
     *
     * @param userId user id.
     * @return list of account view Dto
     */
    List<AccountViewDto> getAllByUserId(Long userId);

    /**
     * Method for blocking accounts by id.
     *
     * @param id account id.
     * @return account view dto
     */
    Optional<AccountViewDto> blockAccount(Long id);

    /**
     * Method for unblocking accounts by id.
     *
     * @param id account id.
     * @return account view dto
     */
    Optional<AccountViewDto> unblockAccount(Long id);
}
