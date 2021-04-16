package com.bank.reckoning.service;

import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * Service interface for work with users.
 */
public interface UserService {

    /**
     * Method for creating user.
     *
     * @param userDto DTO for creating new user.
     * @return user view DTO
     */
    UserViewDto createUser(UserCreateDto userDto);

    /**
     * Method for getting user profile by id.
     *
     * @param id user ID
     * @return user view DTO
     */
    UserViewDto getProfile(Long id);

    /**
     * Method for getting all users.
     *
     * @return list of users view DTO
     */
    List<UserViewDto> getAllUsers();


    /**
     * Method for blocking user by id.
     *
     * @param id user ID
     */
    void blockUser(Long id);

    /**
     * Method for unblocking user by id.
     *
     * @param id user ID
     */
    void unblockUser(Long id);
}
