package reckoning.service;


import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;

import java.util.List;

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
     * Method for updating user profile by id.
     *
     * @param id user ID
     * @param userPatchDto dto fot updating user
     * @return user view DTO
     */
    UserViewDto updateUser(Long id, UserPatchDto userPatchDto);
}
