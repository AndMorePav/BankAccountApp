package reckoning.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reckoning.domain.Status;
import reckoning.domain.User;
import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;
import reckoning.exception.NotFoundException;
import reckoning.exception.RepeatPasswordNotSameException;
import reckoning.mapper.UserMapper;
import reckoning.repository.UserRepository;
import reckoning.security.model.Role;
import reckoning.service.UserService;

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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserViewDto createUser(UserCreateDto userDto) {
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            log.error("Password enter not correct");
            throw new RepeatPasswordNotSameException("Not same", "repeatPassword");
        }

        String encode = passwordEncoder.encode(userDto.getPassword());

        User user = userMapper.map(userDto);
        user.setRole(Role.USER)
                .setPassword(encode)
                .setStatus(Status.ACTIVE)
                .setEnabled(true);

        User savedUser = userRepository.save(user);
        log.info("User {} has been created", savedUser.getUsername());
        return userMapper.map(savedUser);
    }

    @Transactional
    @Override
    public UserViewDto updateUser(Long id, UserPatchDto userPatchDto) {
        User user = userRepository.findById(id).map(u -> userMapper.updateUser(userPatchDto, u))
                .orElseThrow(() -> {
                    log.error("User has not been found with id : {} ", id);
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
                    log.error("User has not been found with id : {} ", id);
                    return new NotFoundException("USER_ID_NOT_FOUND", "id");
                }));
    }

    @Override
    public List<UserViewDto> getAllUsers() {
        return userMapper.map(userRepository.findAll());
    }
}
