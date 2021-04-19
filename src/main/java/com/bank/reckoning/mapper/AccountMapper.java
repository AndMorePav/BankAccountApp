package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.AccountViewDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Mapper of account entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    @Mapping(target = "userId", source = "account.user", qualifiedByName = "userToUserId")
    AccountViewDto map(Account account);

    List<AccountViewDto> map(List<Account> account);

    @Named("userToUserId")
    static Long userToUserId(User user) {
        return user.getId();
    }
}