package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.JournalViewDto;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper of journal entity.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JournalMapper {

    @Mapping(target = "accountId", source = "journal.account.id")
    JournalViewDto map(Journal journal);
}