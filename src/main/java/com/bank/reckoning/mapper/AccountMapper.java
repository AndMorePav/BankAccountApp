package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper of account entity.
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountViewDto map(Account account);

    Account map(AccountCreateDto accountCreateDto);

    List<AccountViewDto> map(List<Account> account);
}