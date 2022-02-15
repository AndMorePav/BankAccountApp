package reckoning.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reckoning.domain.Journal;
import reckoning.dto.JournalViewDto;
import reckoning.mapper.JournalMapper;
import reckoning.repository.JournalRepository;
import reckoning.service.JournalService;

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
