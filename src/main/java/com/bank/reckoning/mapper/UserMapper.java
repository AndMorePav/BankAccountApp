package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper of user entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "accounts", ignore = true)
    User map(UserCreateDto userDto);

    @Mapping(target = "accounts", ignore = true)
    UserViewDto map(User user);

    @Mapping(target = "avatar", ignore = true)
    List<UserViewDto> map(List<User> users);

//    @Named("accountToAccountId")
//    default List<String> accountsToStringAccounts(List<Account> accounts) {
//        if (Objects.nonNull(accounts))
//            return accounts.stream().map(a -> a.getId().toString()).collect(Collectors.toList());
//        else return null;
//    }
}