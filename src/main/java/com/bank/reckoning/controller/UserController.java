package com.bank.reckoning.controller;

import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import com.bank.reckoning.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for works with users.
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
@Api
public class UserController {

    private final UserService userService;

    /**
     * Method for creating new user.
     *
     * @param userCreateDto DTO for creating new user
     * @return DTO of user profile
     */
    @PostMapping("/create")
    @ApiOperation(" Метод для создания нового пользователя.")
    public ResponseEntity<UserViewDto> createUser(@RequestBody @Validated UserCreateDto userCreateDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userCreateDto));
    }

    /**
     * Method for getting user profile by id.
     *
     * @param id user ID
     * @return DTO of user profile
     */
    @GetMapping("/{id}")
    @ApiOperation("Метод для получения пользователя по id.")
    public ResponseEntity<UserViewDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfile(id));
    }

    /**
     * Method for getting user profile by id.
     *
     * @return list of DTOs of users profiles
     */
    @GetMapping
    @ApiOperation("Метод для получения всех пользователей")
    public ResponseEntity<List<UserViewDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
