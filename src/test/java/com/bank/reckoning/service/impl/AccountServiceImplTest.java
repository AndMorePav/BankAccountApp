package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.mapper.AccountMapperImpl;
import com.bank.reckoning.repository.AccountRepository;
import com.bank.reckoning.repository.UserRepository;
import com.bank.reckoning.service.AccountService;
import com.bank.reckoning.service.JournalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Tests for account service impl.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepositoryMock;
    @Mock
    private JournalService journalServiceMock;
    @Mock
    private UserRepository userRepositoryMock;
    private AccountService accountService;
    private Account testAccount;
    private AccountUpdateDto testAccountUpdateDto;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl(accountRepositoryMock, journalServiceMock, new AccountMapperImpl(), userRepositoryMock);

        testAccount = new Account().setId(1L)
                .setAmount(new BigDecimal(100))
                .setEnabled(true)
                .setUser(new User())
                .setAccountJournals(Collections.EMPTY_LIST);

        testAccountUpdateDto = AccountUpdateDto.builder()
                .withAccountId(1L)
                .withAmount("1.0")
                .build();
    }

    @Test
    public void whenUpdateAccount_ReplenishmentOperation_thenReturnAccountViewDto() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testAccount));
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(testAccount);

        accountService.updateAccount(OperationType.REPLENISHMENT, testAccountUpdateDto);

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
    }

    @Test
    public void whenUpdateAccount_WithdrawalOperation_thenReturnAccountViewDto() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testAccount));
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(testAccount);

        accountService.updateAccount(OperationType.WITHDRAWAL, testAccountUpdateDto);

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
    }
}