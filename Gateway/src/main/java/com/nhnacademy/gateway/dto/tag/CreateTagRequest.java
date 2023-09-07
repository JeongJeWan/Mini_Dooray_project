package com.nhnacademy.gateway.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateTagRequest {

    @NotNull
    private final Long projectId;

    @NotBlank
    private final String name;
}
