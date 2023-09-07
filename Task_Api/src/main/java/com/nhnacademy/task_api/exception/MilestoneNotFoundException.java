package com.nhnacademy.task_api.exception;

public class MilestoneNotFoundException extends IllegalArgumentException {
    public MilestoneNotFoundException() {
        super("마일스톤이 존재하지 않습니다.");
    }
}
