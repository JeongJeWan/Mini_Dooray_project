package com.nhnacademy.account_api.exception;

public class UserEmailNotFoundException extends IllegalArgumentException{

    public UserEmailNotFoundException() {
        super("해당 이메일과 일치하는 회원이 존재하지 않습니다.");
    }
}
