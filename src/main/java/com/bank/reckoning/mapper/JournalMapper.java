package com.bank.reckoning.mapper;


import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.JournalViewDto;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper of journal entity.
 */
@Mapper(componentModel = "spring")
public interface JournalMapper {

    JournalViewDto map(Journal journal);
}