package com.gangnam.wholesale.global.error.exception;

public class CodeNotFoundException extends EntityNotFoundException {

    public CodeNotFoundException(String target) {
        super(target + " is not found");
    }
}
