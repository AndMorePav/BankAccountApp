package reckoning.service;

import reckoning.BlockingOperation;
import reckoning.OperationType;
import reckoning.dto.AccountCreateDto;
import reckoning.dto.AccountUpdateDto;
import reckoning.dto.AccountViewDto;

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
     * @param blockingOperation
     * @return account view dto
     */
    AccountViewDto blockingOperations(Long id, BlockingOperation blockingOperation);
}
