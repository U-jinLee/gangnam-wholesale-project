package com.gangnam.wholesale.domain.authentication.service;

import com.gangnam.wholesale.domain.authentication.dto.SignInDto;
import com.gangnam.wholesale.domain.authentication.dto.SignUpDto;
import com.gangnam.wholesale.domain.authentication.dto.ValidateRequestDto;

public interface AuthenticationService {
    SignUpDto.Response signUp(SignUpDto.Request request);
    SignInDto.Response signIn(SignInDto.Request request);
    void validate(String email, ValidateRequestDto request);
}