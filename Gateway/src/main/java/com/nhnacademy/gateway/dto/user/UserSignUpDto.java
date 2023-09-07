package com.nhnacademy.gateway.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
public class UserSignUpDto {

    @NotBlank
    private final String userId;

    @NotBlank
    private String userPassword;

    @NotBlank
    @Email
    private final String userEmail;

    public void passwordEncoder(String userPassword) {
        this.userPassword = userPassword;
    }
}
