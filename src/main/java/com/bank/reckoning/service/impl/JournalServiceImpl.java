package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Journal;
import com.bank.reckoning.dto.JournalViewDto;
import com.bank.reckoning.mapper.JournalMapper;
import com.bank.reckoning.repository.JournalRepository;
import com.bank.reckoning.service.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of service for working with journals.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final JournalMapper journalMapper;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addOperationToJournal(Journal journalRecord) {
        Journal savedRecord = journalRepository.save(journalRecord);
        log.info("Operation for account {} in {} time saved", savedRecord.getAccount(), savedRecord.getOperationTime().toString());
    }

    @Override
    public List<JournalViewDto> getAccountOperations(Long accountId) {
        return journalMapper.map(journalRepository.findByAccountIdOrderByIdDesc(accountId));
    }
}
