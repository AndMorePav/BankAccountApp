package reckoning.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reckoning.domain.Account;
import reckoning.domain.User;
import reckoning.dto.AccountViewDto;

import java.util.List;

/**
 * Mapper of account entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    @Mapping(target = "withUserId", source = "account.user", qualifiedByName = "userToUserId")
    AccountViewDto map(Account account);

    List<AccountViewDto> map(List<Account> account);

    @Named("userToUserId")
    static Long userToUserId(User user) {
        return user.getId();
    }
}