package com.nhnacademy.task_api.exception;

public class TagNotFoundException extends IllegalArgumentException {
    public TagNotFoundException() {
        super("해당 태그가 존재하지 않습니다.");
    }
}
