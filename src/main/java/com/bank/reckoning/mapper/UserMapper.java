package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Objects;

/**
 * Mapper of user entity.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserCreateDto userDto);

    UserViewDto map(User user);

    List<UserViewDto> map(List<User> users);
}