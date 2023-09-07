package com.nhnacademy.task_api.entity;

import lombok.Getter;

@Getter
public enum ProjectStatus {

    ACTIVE("활성"),
    REST("휴먼"),
    TERMINATE("종료");

    private final String status;

    ProjectStatus(String status) {
        this.status = status;
    }
}
