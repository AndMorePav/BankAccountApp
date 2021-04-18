package com.bank.reckoning.controller;

import com.bank.reckoning.domain.OperationType;
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
     */
    @PostMapping("/create")
    @ApiOperation("Метод для создания нового счета пользователя.")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountCreateDto accountCreateDto){
        return accountService.createAccount(accountCreateDto) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    /**
     * Method for updating user account.
     *
     * @param accountUpdateDto for updating user account
     */
    @PostMapping("/update")
    @ApiOperation("Метод для операций по счету пользователя.")
    public ResponseEntity<Void> updateAccount(@RequestParam OperationType operationType, @Valid @RequestBody AccountUpdateDto accountUpdateDto) {
        accountService.updateAccount(operationType, accountUpdateDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for getting users accounts.
     *
     * @param userId for getting all users account
     */
    @GetMapping("/{userId}")
    @ApiOperation("Метод получения всех счетов пользователя.")
    public ResponseEntity<List<AccountViewDto>> getALlAccountsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(accountService.getAllByUserId(userId));
    }

    /**
     * Method for blocking account by id.
     *
     * @param id account id
     */
    @PostMapping("/block")
    @ApiOperation("Метод для блокировки счета.")
    public ResponseEntity<Void> blockUser(@RequestBody Long id) {
        accountService.blockAccount(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Method for unblocking account by id.
     *
     * @param id account id
     */
    @PostMapping("/unblock")
    @ApiOperation("Метод для разблокировки счета.")
    public ResponseEntity<Void> unBlockUser(@RequestBody Long id) {
        accountService.unblockAccount(id);

        return ResponseEntity.ok().build();
    }
}
