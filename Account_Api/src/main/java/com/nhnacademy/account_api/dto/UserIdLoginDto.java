package com.nhnacademy.account_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserIdLoginDto {
    @NotBlank
    private String userId;
}
