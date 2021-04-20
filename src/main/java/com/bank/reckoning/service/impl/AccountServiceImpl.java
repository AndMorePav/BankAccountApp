package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.mapper.AccountMapper;
import com.bank.reckoning.repository.AccountRepository;
import com.bank.reckoning.repository.UserRepository;
import com.bank.reckoning.service.AccountService;
import com.bank.reckoning.service.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public Optional<AccountViewDto> createAccount(AccountCreateDto accountCreateDto) {
        Account newAccount = new Account().setEnabled(true).setAmount(new BigDecimal(0));

        return  userRepository.findById(accountCreateDto.getUserId())
                .map(newAccount::setUser)
                .map(accountRepository::save)
                .map(accountMapper::map);
    }

    @Transactional
    @Override
    public Optional<AccountViewDto> updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(accountUpdateDto.getAccountId()).orElseThrow(NotFoundException::new);

        if (!account.isEnabled())
            return Optional.empty();

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

        return Optional.of(accountMapper.map(savedAccount));
    }

    @Override
    public List<AccountViewDto> getAllByUserId(Long userId) {
        return accountMapper.map(accountRepository.findByUserId(userId));
    }

    @Transactional
    @Override
    public Optional<AccountViewDto> blockAccount(Long id) {
        return accountRepository.findById(id).map(account -> account.setEnabled(false))
                .map(accountRepository::save)
                .map(accountMapper::map);
    }

    @Transactional
    @Override
    public Optional<AccountViewDto> unblockAccount(Long id) {
        return accountRepository.findById(id).map(account -> account.setEnabled(true))
                .map(accountRepository::save)
                .map(accountMapper::map);
    }
}
