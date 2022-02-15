package reckoning.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import reckoning.domain.User;
import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(UserPatchDto userPatchDto, @MappingTarget User user);

    List<UserViewDto> map(List<User> users);
}