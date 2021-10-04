package com.bank.reckoning.service;

import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.domain.enums.BlockingOperation;

import java.util.List;

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
    AccountViewDto createAccount(AccountCreateDto accountCreateDto);

    /**
     * Method for updating account.
     *  @param operationType    type of operation.
     * @param accountUpdateDto DTO for updating account.
     * @return account view dto
     */
    AccountViewDto updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto);

    /**
     * Method for getting accounts by user id.
     *
     * @param userId user id.
     * @return list of account view Dto
     */
    List<AccountViewDto> getAllByUserId(Long userId);

    /**
     * Method for blocking status of account by id.
     *
     * @param id account id.
//     * @param operation
     * @return account view dto
     */
    AccountViewDto blockingOperations(Long id, BlockingOperation blockingOperation);
}
