package com.nhnacademy.gateway.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String userId;

    private String userPassword;

    private String userEmail;

    private String status;

}
