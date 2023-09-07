package com.nhnacademy.account_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account_api.dto.UserDto;
import com.nhnacademy.account_api.dto.UserIdLoginDto;
import com.nhnacademy.account_api.dto.UserSignUpDto;
import com.nhnacademy.account_api.entity.User;
import com.nhnacademy.account_api.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    private final String userId = "marco";
    private final String userPassword = "1234";
    private final String userEmail = "marco@nhnacademy";

    @Test
    @DisplayName("회원 가입")
    void testSignUp() throws Exception {
        UserSignUpDto signUp = new UserSignUpDto();

        ReflectionTestUtils.setField(signUp, "userId", userId);
        ReflectionTestUtils.setField(signUp, "userPassword", userPassword);
        ReflectionTestUtils.setField(signUp, "userEmail", userEmail);

        doNothing().when(userService).doSignUp(signUp);

        mockMvc.perform(post("/account/users/signup")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUp)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인")
    void testLogin() throws Exception {

        UserIdLoginDto userLogin = new UserIdLoginDto();
        ReflectionTestUtils.setField(userLogin, "userId", userId);

        User user = User.builder().userId(userId).userPassword(userPassword).userEmail(userEmail).build();

        UserDto actual = new UserDto(user);

        given(userService.doLogin(any()))
                .willReturn(actual);

        mockMvc.perform(post("/account/users/login")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(mapper.writeValueAsString(userLogin)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", equalTo(userId)))
                .andExpect(jsonPath("$.userPassword", equalTo(userPassword)))
                .andDo(print());
    }
}