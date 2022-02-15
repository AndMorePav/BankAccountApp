package reckoning.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;
import reckoning.service.UserService;

import java.util.List;

/**
 * Controller for work with users.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserViewDto> createUser(@RequestBody @Validated UserCreateDto userCreateDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userCreateDto));
    }

    @Override
    public ResponseEntity<UserViewDto> update(@PathVariable Long id, @RequestBody UserPatchDto userPatchDto) {
        return ResponseEntity.ok(userService.updateUser(id, userPatchDto));
    }

    @Override
    public ResponseEntity<UserViewDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfile(id));
    }

    @Override
    public ResponseEntity<List<UserViewDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
