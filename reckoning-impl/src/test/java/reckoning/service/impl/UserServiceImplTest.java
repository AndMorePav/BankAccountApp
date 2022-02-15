package reckoning.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import reckoning.domain.User;
import reckoning.dto.UserCreateDto;
import reckoning.dto.UserPatchDto;
import reckoning.dto.UserViewDto;
import reckoning.exception.NotFoundException;
import reckoning.exception.RepeatPasswordNotSameException;
import reckoning.mapper.UserMapperImpl;
import reckoning.repository.UserRepository;
import reckoning.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for user service impl.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    PasswordEncoder passwordEncoder;
    private UserService userService;
    private User testUser;
    private UserViewDto userViewDto;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepositoryMock, new UserMapperImpl(), passwordEncoder);

        testUser = new User()
                .setId(1L)
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setAccounts(Collections.emptyList());

        userViewDto = new UserViewDto()
                .setId(1L)
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setAccounts(Collections.emptyList());
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
        return new UserCreateDto()
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setPassword("password")
                .setRepeatPassword("password");
    }

    private UserCreateDto getUserCreateDtoWithWrongPassword() {
        return new UserCreateDto()
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest")
                .setPassword("password")
                .setRepeatPassword("NotSamePassword");
    }

    private UserPatchDto getUserPatchDto() {
        return new UserPatchDto()
                .setFirstName("testtest")
                .setLastName("testtest")
                .setUsername("testtest");
    }
}