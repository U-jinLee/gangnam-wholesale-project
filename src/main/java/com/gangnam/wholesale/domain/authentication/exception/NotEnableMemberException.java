package com.gangnam.wholesale.domain.authentication.exception;

import com.gangnam.wholesale.global.error.exception.BusinessException;
import com.gangnam.wholesale.global.error.exception.ErrorCode;

public class NotEnableMemberException extends BusinessException {

    public NotEnableMemberException(String message) {
        super(message, ErrorCode.NOT_ENABLE_MEMBER);
    }

}
