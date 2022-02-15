package reckoning.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reckoning.dto.JournalViewDto;
import reckoning.service.JournalService;

import java.util.List;

/**
 * Controller for works with journals.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JournalControllerImpl implements JournalController {

    private final JournalService journalService;

    @Override
    public ResponseEntity<List<JournalViewDto>> getALlAccountsByUserId(@PathVariable Long accountId) {
        return ResponseEntity.ok(journalService.getAccountOperations(accountId));
    }
}
