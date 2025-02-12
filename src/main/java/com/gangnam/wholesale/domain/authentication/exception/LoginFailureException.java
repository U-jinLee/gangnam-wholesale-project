package com.gangnam.wholesale.domain.authentication.exception;

import com.gangnam.wholesale.global.error.exception.BusinessException;
import com.gangnam.wholesale.global.error.exception.ErrorCode;

public class LoginFailureException extends BusinessException {
    public LoginFailureException() {
        super(ErrorCode.LOGIN_FAILURE);
    }
}
