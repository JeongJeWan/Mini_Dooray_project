package com.nhnacademy.account_api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailRequest {

    @Email
    @NotBlank
    private String userEmail;
}
