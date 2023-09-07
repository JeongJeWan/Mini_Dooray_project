package com.nhnacademy.task_api.exception;

public class ProjectNotFoundException extends IllegalArgumentException {
    public ProjectNotFoundException() {
        super("해당 프로젝트가 존재하지 않습니다.");
    }
}
