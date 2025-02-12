package com.gangnam.wholesale.domain.authentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidateRequestDto {
    private String code;
}