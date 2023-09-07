package com.nhnacademy.gateway.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ModifyTagRequest {

    @NotNull
    private final Long id;

    @NotBlank
    private final String name;
}
