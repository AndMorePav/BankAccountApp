package com.bank.reckoning.service.impl;

import com.bank.reckoning.domain.Account;
import com.bank.reckoning.domain.User;
import com.bank.reckoning.dto.UserCreateDto;
import com.bank.reckoning.dto.UserPatchDto;
import com.bank.reckoning.dto.UserViewDto;
import com.bank.reckoning.exception.NotFoundException;
import com.bank.reckoning.exception.RepeatPasswordNotSameException;
import com.bank.reckoning.mapper.UserMapper;
import com.bank.reckoning.mapper.UserMapperImpl;
import com.bank.reckoning.repository.UserRepository;
import com.bank.reckoning.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for user service impl.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    private UserService userService;
    private User testUser;
    private UserViewDto userViewDto;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepositoryMock, new UserMapperImpl());

        testUser = new User()
                .setId(1L)
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setAccounts(Collections.emptyList());

        userViewDto = UserViewDto.builder()
                .withId(1L)
                .withFirstName("testtest")
                .withLastName("testtest")
                .withUsername("testtest")
                .withAccounts(Collections.emptyList())
                .build();
    }

    @Test
    public void whenCreateUser_ThenReturnUserViewDto() {
        when(userRepositoryMock.save(any(User.class))).thenReturn(testUser);

        UserViewDto resultUserDto = userService.createUser(getUserCreateDto());

        verify(userRepositoryMock, times(1)).save(any(User.class));
        assertEquals(userViewDto, resultUserDto);
    }

    @Test(expected = RepeatPasswordNotSameException.class)
    public void whenCreateUser_withNotSamePassword_ThenRepeatPasswordNotSameException() {
        userService.createUser(getUserCreateDtoWithWrongPassword());
    }

    @Test
    public void whenUpdateUser_ThenReturnUserViewDto() {
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(userRepositoryMock.save(any(User.class))).thenReturn(testUser);

        UserViewDto resultUserDto = userService.updateUser(1L, getUserPatchDto());

        verify(userRepositoryMock, times(1)).findById(anyLong());
        verify(userRepositoryMock, times(1)).save(any(User.class));
        assertEquals(userViewDto, resultUserDto);
    }

    @Test(expected = NotFoundException.class)
    public void whenUpdateNotExistingUser_ThenNotFoundException() {
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        userService.updateUser(1L, getUserPatchDto());

        verify(userRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    public void whenGetProfile_ThenReturnUserViewDto() {
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(testUser));

        UserViewDto resultUserDto = userService.getProfile(1L);

        verify(userRepositoryMock, times(1)).findById(anyLong());
        assertEquals(userViewDto, resultUserDto);
    }

    private UserCreateDto getUserCreateDto() {
        return UserCreateDto.builder()
                .withFirstName("testtest")
                .withLastName("testtest")
                .withUsername("testtest")
                .withPassword("password")
                .withRepeatPassword("password")
                .build();
    }

    private UserCreateDto getUserCreateDtoWithWrongPassword() {
        return UserCreateDto.builder()
                .withFirstName("testtest")
                .withLastName("testtest")
                .withUsername("testtest")
                .withPassword("password")
                .withRepeatPassword("NotSamePassword")
                .build();
    }

    private UserPatchDto getUserPatchDto() {
        return UserPatchDto.builder()
                .withFirstName("testtest")
                .withLastName("testtest")
                .withUsername("testtest")
                .build();
    }
}