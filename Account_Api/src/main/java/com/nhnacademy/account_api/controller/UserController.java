package com.nhnacademy.account_api.controller;

import com.nhnacademy.account_api.dto.EmailRequest;
import com.nhnacademy.account_api.dto.UserDto;
import com.nhnacademy.account_api.dto.UserIdLoginDto;
import com.nhnacademy.account_api.dto.UserSignUpDto;
import com.nhnacademy.account_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findUsers() {
        List<UserDto> userDtoList = userService.findAll();

        return ResponseEntity.status(OK)
                .contentType(APPLICATION_JSON)
                .body(userDtoList);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserSignUpDto userSignUpDto) {
        userService.doSignUp(userSignUpDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid UserIdLoginDto userIdLoginDto) {
        UserDto user = userService.doLogin(userIdLoginDto);

        return ResponseEntity.status(OK)
                .contentType(APPLICATION_JSON)
                .body(user);
    }

    @PostMapping("/email")
    public ResponseEntity<UserDto> findByEmail(@RequestBody @Valid EmailRequest emailRequest) {

        log.info("<><<><>><> USER EMAIL : {}", emailRequest.getUserEmail());

        UserDto userByEmail = userService.findByUserByEmail(emailRequest.getUserEmail());

        return ResponseEntity.status(OK)
                .contentType(APPLICATION_JSON)
                .body(userByEmail);
    }

    @PutMapping("/changeStatusLeave/{id}")
    public ResponseEntity<Void> changeStatusLeave(@PathVariable("id") Long id) {
        userService.changeUserStatusLeave(id);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/changeStatusRest/{id}")
    public ResponseEntity<Void> changeStatusRest(@PathVariable("id") Long id) {
        userService.changeUserStatusRest(id);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
































