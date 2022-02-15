package reckoning.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import reckoning.domain.Account;
import reckoning.domain.Journal;
import reckoning.dto.JournalViewDto;

import java.util.List;
import java.util.Objects;

/**
 * Mapper of journal entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JournalMapper {

    @Mapping(target = "accountId", source = "journal.account", qualifiedByName = "accountToAccountId")
    JournalViewDto map(Journal journal);

    List<JournalViewDto> map(List<Journal> journal);

    @Named("accountToAccountId")
    default Long accountsToStringAccounts(Account account) {
        if (Objects.nonNull(account.getId()))
            return account.getId();
        else return null;
    }
}