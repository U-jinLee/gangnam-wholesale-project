package com.gangnam.wholesale.domain.authentication.exception;

import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(String target) {
        super(target + " is not found");
    }
}
