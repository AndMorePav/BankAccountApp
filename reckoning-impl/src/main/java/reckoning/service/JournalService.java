package reckoning.service;


import reckoning.domain.Journal;
import reckoning.dto.JournalViewDto;

import java.util.List;

/**
 * Service interface for work with journal.
 */
public interface JournalService {

    /**
     * Method for adding log in journal.
     *
     * @param journal journal log
     */
    void addOperationToJournal(Journal journal);

    /**
     * Method for getting account journals.
     *
     * @param accountId account id.
     * @return list of journals view DTO
     */
    List<JournalViewDto> getAccountOperations(Long accountId);
}
