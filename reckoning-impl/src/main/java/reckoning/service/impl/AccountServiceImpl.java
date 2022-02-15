package reckoning.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reckoning.BlockingOperation;
import reckoning.OperationType;
import reckoning.domain.Account;
import reckoning.domain.Journal;
import reckoning.dto.AccountCreateDto;
import reckoning.dto.AccountUpdateDto;
import reckoning.dto.AccountViewDto;
import reckoning.exception.AccountNotEnableException;
import reckoning.mapper.AccountMapper;
import reckoning.repository.AccountRepository;
import reckoning.repository.UserRepository;
import reckoning.service.AccountService;
import reckoning.service.JournalService;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of service for working with accounts.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JournalService journalService;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public AccountViewDto createAccount(AccountCreateDto accountCreateDto) {
        Account newAccount = new Account().setEnabled(true).setAmount(new BigDecimal(0));

        Account account = userRepository.findById(accountCreateDto.getUserId())
                .map(newAccount::setUser)
                .orElseThrow(NotFoundException::new);

        log.info("Account of user {} created", account.getUser().getUsername());

        return accountMapper.map(accountRepository.save(account));
    }

    @Transactional
    @Override
    public AccountViewDto updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getAccountId()).orElseThrow(NotFoundException::new);

        if (!account.isEnabled()) {
            log.info("Account {} blocked", account.getId());
            throw new AccountNotEnableException();
        }

        BigDecimal amountOfUser = account.getAmount();
        BigDecimal amountOfOperation = new BigDecimal(accountUpdateDto.getAmount());
        BigDecimal result;

        if (operationType.equals(OperationType.WITHDRAWAL)) {
            result = amountOfUser.subtract(amountOfOperation).setScale(2, RoundingMode.HALF_DOWN);
        } else {
            result = amountOfUser.add(amountOfOperation).setScale(2, RoundingMode.HALF_DOWN);
        }

        account.setAmount(result);

        Account savedAccount = accountRepository.save(account);

        log.debug("Account of user {} changed", account.getUser().getUsername());

        Journal journal = new Journal().setAccount(savedAccount)
                .setInitialAmount(amountOfUser)
                .setFinalAmount(result)
                .setOperationType(operationType)
                .setOperationTime(LocalDateTime.now());

        journalService.addOperationToJournal(journal);

        return accountMapper.map(savedAccount);
    }

    @Override
    public List<AccountViewDto> getAllByUserId(Long userId) {
        return accountMapper.map(accountRepository.findByUserId(userId));
    }

    @Transactional
    @Override
    public AccountViewDto blockingOperations(Long id, BlockingOperation blockingOperation) {
        Account blockingAccount = accountRepository.findById(id)
                .map(account -> getAccount(blockingOperation, account))
                .orElseThrow(NotFoundException::new);

        return accountMapper.map(accountRepository.save(blockingAccount));
    }

    private Account getAccount(BlockingOperation blockingOperation, Account account) {
        return BlockingOperation.BLOCK == blockingOperation
                ? account.setEnabled(false)
                : account.setEnabled(true);
    }
}
