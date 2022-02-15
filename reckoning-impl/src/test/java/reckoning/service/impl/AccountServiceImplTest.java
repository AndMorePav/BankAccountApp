package reckoning.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reckoning.OperationType;
import reckoning.domain.Account;
import reckoning.domain.User;
import reckoning.dto.AccountCreateDto;
import reckoning.dto.AccountUpdateDto;
import reckoning.dto.AccountViewDto;
import reckoning.exception.AccountNotEnableException;
import reckoning.mapper.AccountMapperImpl;
import reckoning.repository.AccountRepository;
import reckoning.repository.UserRepository;
import reckoning.service.AccountService;
import reckoning.service.JournalService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    private User testUser;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl(accountRepositoryMock, journalServiceMock, new AccountMapperImpl(), userRepositoryMock);

        testAccount = new Account().setId(1L)
                .setAmount(new BigDecimal(100))
                .setEnabled(true)
                .setUser(new User())
                .setAccountJournals(Collections.EMPTY_LIST);

        testAccountUpdateDto = new AccountUpdateDto(1L)
                .setAmount("1.0");

        testUser = new User()
                .setId(1L)
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setAccounts(Collections.emptyList());
    }


    @Test
    public void whenCreateAccount_thenReturnAccountViewDto() {
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(testAccount);

        AccountViewDto resultAccountViewDto = accountService.createAccount(getAccountCreateDto());

        verify(userRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
        assertEquals(getAccountViewDto(), resultAccountViewDto);
    }

    @Test(expected = AccountNotEnableException.class)
    public void whenUpdateAccount_accountNotEnabled_thenNotSave() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testAccount.setEnabled(false)));

        accountService.updateAccount(OperationType.REPLENISHMENT, testAccountUpdateDto);

        verify(accountRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    public void whenUpdateAccount_ReplenishmentOperation_thenSave() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testAccount));
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(testAccount);

        AccountViewDto accountViewDto = accountService.updateAccount(OperationType.REPLENISHMENT, testAccountUpdateDto);

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
        assertEquals("101.00",accountViewDto.getAmount());
    }

    @Test
    public void whenUpdateAccount_WithdrawalOperation_thenSave() {
        when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testAccount));
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(testAccount);

        AccountViewDto accountViewDto = accountService.updateAccount(OperationType.WITHDRAWAL, testAccountUpdateDto);

        verify(accountRepositoryMock, times(1)).findById(anyLong());
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
        assertEquals("99.00",accountViewDto.getAmount());
    }

    private AccountViewDto getAccountViewDto() {
        return new AccountViewDto()
                .setId(1L)
                .setAmount("100")
                .setEnabled("true");
    }

    private AccountCreateDto getAccountCreateDto() {
        return new AccountCreateDto(1L);
    }
}