package com.nhnacademy.account_api.dto;

import com.nhnacademy.account_api.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserDto {
    private Long id;

    private String userId;

    private String userPassword;

    private String userEmail;

    private String status;
    public UserDto(User user){
        this.id = user.getId();
        this.userId=user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userEmail = user.getUserEmail();
        this.status = user.getStatus().getStatus();
    }
}
