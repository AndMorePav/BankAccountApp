package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Role;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserPatchDto;
import com.bank.reckoning.dto.UserViewDto;
import com.bank.reckoning.exception.NotFoundException;
import com.bank.reckoning.exception.RepeatPasswordNotSameException;
import com.bank.reckoning.mapper.UserMapper;
import com.bank.reckoning.repository.UserRepository;
import com.bank.reckoning.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of service for working with users.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserViewDto createUser(UserCreateDto userDto) {
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            log.error("User enter not correct");
            throw new RepeatPasswordNotSameException("Not same", "repeatPassword");
        }

        User user = userMapper.map(userDto);
        user.setRole(Role.ROLE_USER)
                .setEnabled(true);
        log.info("User has been created");
        User savedUser = userRepository.save(user);
        return userMapper.map(savedUser);
    }

    @Transactional
    @Override
    public UserViewDto updateUser(Long id, UserPatchDto userPatchDto) {
        User user = userRepository.findById(id).map(u -> userMapper.updateUser(userPatchDto, u))
                .orElseThrow(() -> {
                    log.error("User has not been found with id : " + id);
                    return new NotFoundException("USER_ID_NOT_FOUND", "id");
                });
        User updatedUser = userRepository.save(user);
        log.info("User has been updated with id : " + id);
        return userMapper.map(updatedUser);
    }

    @Override
    public UserViewDto getProfile(Long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User has not been found with id : " + id);
                    return new NotFoundException("USER_ID_NOT_FOUND", "id");
                }));
    }

    @Override
    public List<UserViewDto> getAllUsers() {
        return userMapper.map(userRepository.findAll());
    }
}
