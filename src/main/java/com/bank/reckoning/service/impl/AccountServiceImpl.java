package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.mapper.AccountMapper;
import com.bank.reckoning.repository.AccountRepository;
import com.bank.reckoning.service.AccountService;
import com.bank.reckoning.service.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

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

    @Transactional
    @Override
    public boolean createAccount(AccountCreateDto accountCreateDto) {
        return accountRepository.saveByUserId(accountCreateDto.getUserId()) > 0;
    }

    @Transactional
    @Async
    @Override
    public void updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getAccountId()).orElseThrow(NotFoundException::new);

        if (!account.isEnabled())
            return;

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

        Journal journal = new Journal().setAccount(savedAccount)
                .setInitialAmount(amountOfUser)
                .setFinalAmount(result)
                .setOperationType(operationType)
                .setOperationTime(LocalDateTime.now());

        journalService.addOperationToJournal(journal);
    }

    @Override
    public List<AccountViewDto> getAllByUserId(Long userId) {
        return accountMapper.map(accountRepository.findByUserId(userId));
    }

    @Transactional
    @Override
    public void blockAccount(Long id) {
        accountRepository.findById(id).map(account -> account.setEnabled(false)).map(accountRepository::save).orElseThrow(UnknownError::new);
    }

    @Transactional
    @Override
    public void unblockAccount(Long id) {
        accountRepository.findById(id).map(account -> account.setEnabled(true)).map(accountRepository::save);
    }
}
