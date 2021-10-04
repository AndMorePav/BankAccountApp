package com.bank.reckoning.controller;

import com.bank.reckoning.domain.OperationType;
import com.bank.reckoning.domain.enums.BlockingOperation;
import com.bank.reckoning.dto.AccountCreateDto;
import com.bank.reckoning.dto.AccountUpdateDto;
import com.bank.reckoning.dto.AccountViewDto;
import com.bank.reckoning.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for works with accounts.
 */
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin
@Api
public class AccountController {

    private final AccountService accountService;

    /**
     * Method for creating new user account.
     *
     * @param accountCreateDto for creating new user account
     * @return account view dto
     */
    @PostMapping("/create")
    @ApiOperation("Метод для создания нового счета пользователя.")
    public ResponseEntity<AccountViewDto> createAccount(@Valid @RequestBody AccountCreateDto accountCreateDto) {
        return ResponseEntity.ok(accountService.createAccount(accountCreateDto));
    }

    /**
     * Method for updating user account.
     *
     * @param accountUpdateDto for updating user account
     */
    @PostMapping("/update")
    @ApiOperation("Метод для операций по счету пользователя.")
    public ResponseEntity<AccountViewDto> updateAccount(@RequestParam OperationType operationType, @Valid @RequestBody AccountUpdateDto accountUpdateDto) {
        return ResponseEntity.ok(accountService.updateAccount(operationType, accountUpdateDto));
    }

    /**
     * Method for getting users accounts.
     *
     * @param userId for getting all users account
     */
    @GetMapping("/{userId}")
    @ApiOperation("Метод получения всех счетов пользователя.")
    public ResponseEntity<List<AccountViewDto>> getALlAccountsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAllByUserId(userId));
    }

    /**
     * Method for blocking account by id.
     *
     * @param id account id
     * @return account view dto
     */
    @PostMapping("/block")
    @ApiOperation("Метод для блокировки счета.")
    public ResponseEntity<AccountViewDto> blockUser(@RequestBody Long id, @RequestParam BlockingOperation blockingOperation) {
        return ResponseEntity.ok(accountService.blockingOperations(id, blockingOperation));
    }
}
