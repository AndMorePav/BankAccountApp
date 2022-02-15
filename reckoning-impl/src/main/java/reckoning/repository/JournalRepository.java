package reckoning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reckoning.domain.Journal;

import java.util.List;

/**
 * Repository for work with journals table.
 */
public interface JournalRepository extends JpaRepository<Journal, Long> {

    /**
     * Method for finding by account id
     *
     * @param accountId account Id
     * @return list of journals
     */
    List<Journal> findByAccountIdOrderByIdDesc(Long accountId);
}
