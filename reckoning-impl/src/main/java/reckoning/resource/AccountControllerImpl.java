package reckoning.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reckoning.BlockingOperation;
import reckoning.OperationType;
import reckoning.dto.AccountCreateDto;
import reckoning.dto.AccountUpdateDto;
import reckoning.dto.AccountViewDto;
import reckoning.service.AccountService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for work with accounts.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public ResponseEntity<AccountViewDto> createAccount(@Valid @RequestBody AccountCreateDto accountCreateDto) {
        return ResponseEntity.ok(accountService.createAccount(accountCreateDto));
    }

    @Override
    public ResponseEntity<AccountViewDto> updateAccount(@RequestParam OperationType operationType, @Valid @RequestBody AccountUpdateDto accountUpdateDto) {
        return ResponseEntity.ok(accountService.updateAccount(operationType, accountUpdateDto));
    }

    @Override
    public ResponseEntity<List<AccountViewDto>> getALlAccountsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAllByUserId(userId));
    }

    @Override
    public ResponseEntity<AccountViewDto> blockUser(@RequestBody Long id, @RequestParam BlockingOperation blockingOperation) {
        return ResponseEntity.ok(accountService.blockingOperations(id, blockingOperation));
    }
}
