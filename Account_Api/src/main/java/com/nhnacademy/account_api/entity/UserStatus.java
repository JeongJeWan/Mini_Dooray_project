package com.nhnacademy.account_api.entity;


import lombok.Getter;

public enum UserStatus {

    JOIN("가입"),
    LEAVE("탈퇴"),
    REST("휴식");

    UserStatus(String status) {
        this.status = status;
    }

    @Getter
    private final String status;
}
