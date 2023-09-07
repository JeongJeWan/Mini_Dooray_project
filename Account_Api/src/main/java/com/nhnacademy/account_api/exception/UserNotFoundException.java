package com.nhnacademy.account_api.exception;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(){
        super("해당 사용자를 찾을 수 없습니다.");
    }
}
