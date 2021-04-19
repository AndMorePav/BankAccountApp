package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.dto.JournalViewDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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