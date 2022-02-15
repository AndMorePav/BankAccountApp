package reckoning.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reckoning.BlockingOperation;
import reckoning.OperationType;
import reckoning.dto.AccountCreateDto;
import reckoning.dto.AccountUpdateDto;
import reckoning.dto.AccountViewDto;

import java.util.List;

@Api
@RequestMapping("/accounts")
public interface AccountController {

    /**
     * Method for creating new user account.
     *
     * @param accountCreateDto for creating new user account
     * @return account view dto
     */
    @PostMapping("/create")
    @ApiOperation("Метод для создания нового счета пользователя.")
    ResponseEntity<AccountViewDto> createAccount(AccountCreateDto accountCreateDto);

    /**
     * Method for updating user account.
     *
     * @param accountUpdateDto for updating user account
     */
    @PostMapping("/update")
    @ApiOperation("Метод для операций по счету пользователя.")
    ResponseEntity<AccountViewDto> updateAccount(OperationType operationType, AccountUpdateDto accountUpdateDto);

    /**
     * Method for getting users accounts.
     *
     * @param userId for getting all users account
     */
    @GetMapping("/{userId}")
    @ApiOperation("Метод получения всех счетов пользователя.")
    ResponseEntity<List<AccountViewDto>> getALlAccountsByUserId(Long userId);

    /**
     * Method for blocking account by id.
     *
     * @param id account id
     * @return account view dto
     */
    @PostMapping("/block")
    @ApiOperation("Метод для блокировки счета.")
    ResponseEntity<AccountViewDto> blockUser(Long id, BlockingOperation blockingOperation);
}
