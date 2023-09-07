package com.nhnacademy.account_api.controller;

import com.nhnacademy.account_api.exception.UserEmailNotFoundException;
import com.nhnacademy.account_api.exception.UserIdDuplicationException;
import com.nhnacademy.account_api.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {
            UserIdDuplicationException.class,
            UserNotFoundException.class,
            UserEmailNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handlerException(RuntimeException ex) {

        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
