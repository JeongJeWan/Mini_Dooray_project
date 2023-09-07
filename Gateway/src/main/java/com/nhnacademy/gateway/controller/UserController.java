package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.user.UserSignUpDto;
import com.nhnacademy.gateway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserSignUpDto userSignUpDto) {
        return "user/signup-form";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute @Valid UserSignUpDto userSignUpDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/signup-form";
        }

        userService.signUp(userSignUpDto);
        return "redirect:/";
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login-form";
    }

    @GetMapping("/users/logout")
    public String logout() {
        return "user/logout";
    }
}
