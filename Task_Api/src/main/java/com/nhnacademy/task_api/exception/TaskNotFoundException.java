package com.nhnacademy.task_api.exception;

public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException() {
        super("해당 업무가 존재하지 않습니다.");
    }
}
