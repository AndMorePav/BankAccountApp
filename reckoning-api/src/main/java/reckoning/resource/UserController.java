package reckoning.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;

import java.util.List;

@RequestMapping("/users")
@Api
public interface UserController {

    /**
     * Method for creating new user.
     *
     * @param userCreateDto DTO for creating new user
     * @return DTO of user profile
     */
    @PostMapping("/create")
    @ApiOperation(" Метод для создания нового пользователя.")
    ResponseEntity<UserViewDto> createUser(UserCreateDto userCreateDto);

    /**
     * Method for updating existing user.
     *
     * @param id           user ID
     * @param userPatchDto DTO for updating user
     * @return DTO of updating user profile
     */
    @ApiOperation("Метод для обновления данных существующего пользователя.")
    @PatchMapping("/update/{id}")
    ResponseEntity<UserViewDto> update(Long id, UserPatchDto userPatchDto);

    /**
     * Method for getting user profile by id.
     *
     * @param id user ID
     * @return DTO of user profile
     */
    @GetMapping("/{id}")
    @ApiOperation("Метод для получения пользователя по id.")
    ResponseEntity<UserViewDto> getUser(Long id);

    /**
     * Method for getting user profile by id.
     *
     * @return list of DTOs of users profiles
     */
    @GetMapping
    @ApiOperation("Метод для получения всех пользователей")
    ResponseEntity<List<UserViewDto>> getAllUsers();
}
