package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of user entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "accounts", ignore = true)
    User map(UserCreateDto userDto);

    @Mapping(target = "accounts", source = "user.accounts")
    UserViewDto map(User user);

    List<UserViewDto> map(List<User> users);
}