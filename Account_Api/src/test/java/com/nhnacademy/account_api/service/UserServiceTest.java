package com.nhnacademy.account_api.service;

import com.nhnacademy.account_api.dto.UserDto;
import com.nhnacademy.account_api.dto.UserIdLoginDto;
import com.nhnacademy.account_api.dto.UserSignUpDto;
import com.nhnacademy.account_api.entity.User;
import com.nhnacademy.account_api.entity.UserStatus;
import com.nhnacademy.account_api.exception.UserIdDuplicationException;
import com.nhnacademy.account_api.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private String userId = "marco";
    private String userPassword = "1234";
    private String userEmail = "marco@nhnacademy.com";

    @Test
    @DisplayName("회원 가입")
    void testSignUp() {
        User user = getUser();

        UserSignUpDto userSignUp = new UserSignUpDto();

        ReflectionTestUtils.setField(userSignUp, "userId", userId);
        ReflectionTestUtils.setField(userSignUp, "userPassword", userPassword);
        ReflectionTestUtils.setField(userSignUp, "userEmail", userEmail);

        given(userRepository.save(any())).willReturn(user);

        userService.doSignUp(userSignUp);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("로그인 요청")
    void testDoLogin() {
        UserIdLoginDto userLogin = new UserIdLoginDto();

        ReflectionTestUtils.setField(userLogin, "userId", userId);

        User user = getUser();

        UserDto userDto = new UserDto(user);

        given(userRepository.findByUserId(userLogin.getUserId())).willReturn(Optional.of(user));

        UserDto userLoginActual = userService.doLogin(userLogin);

        verify(userRepository, times(1)).findByUserId(anyString());
        assertThat(userLoginActual.getUserId()).isEqualTo(userDto.getUserId());
        assertThat(userLoginActual.getUserPassword()).isEqualTo(userDto.getUserPassword());
    }

    @Test
    @DisplayName("이메일로 회원 찾기")
    void testFindUserByEmail() {

        User user = getUser();

        given(userRepository.findByUserEmail(userEmail)).willReturn(Optional.of(user));

        UserDto actual = userService.findByUserByEmail(userEmail);

        verify(userRepository, times(1)).findByUserEmail(anyString());
        assertThat(actual.getUserEmail()).isEqualTo(userEmail);
    }

    @Test
    @DisplayName("회원 삭제")
    void testUserLeave() {
        User user = spy(getUser());

        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        lenient().when(user.getId()).thenReturn(1L);

        userService.changeUserStatusLeave(1L);

        verify(user, times(1)).changeLeaveUser();
        assertThat(user.getStatus()).isEqualTo(UserStatus.LEAVE);
    }

    @Test
    @DisplayName("회원 휴면")
    void testUserRest() {
        User user = spy(getUser());

        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        lenient().when(user.getId()).thenReturn(1L);

        userService.changeUserStatusRest(1L);

        verify(user, times(1)).changeRestUser();
        assertThat(user.getStatus()).isEqualTo(UserStatus.REST);
    }

    @Test
    @DisplayName("아이디 중복으로 회원가입 실패")
    void testUserIdDuplication() {

        User user = getUser();

        UserSignUpDto userSignUp = new UserSignUpDto();

        ReflectionTestUtils.setField(userSignUp, "userId", userId);
        ReflectionTestUtils.setField(userSignUp, "userPassword", userPassword);
        ReflectionTestUtils.setField(userSignUp, "userEmail", userEmail);

        given(userRepository.save(any())).willReturn(user);

        userService.doSignUp(userSignUp);

        given(userRepository.existsByUserId(userId)).willReturn(true);

        assertThatThrownBy(() -> userService.doSignUp(userSignUp))
                .isInstanceOf(UserIdDuplicationException.class);

    }

    private User getUser() {
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userEmail(userEmail)
                .build();
    }
}