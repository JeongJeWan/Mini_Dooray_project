package com.nhnacademy.account_api.exception;

public class UserIdDuplicationException extends IllegalArgumentException{
    public UserIdDuplicationException(String userId){
        super(userId + "중복된 사용자입니다.");
    }
}
