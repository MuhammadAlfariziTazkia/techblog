package com.alfarizi.techblog.exception.custom;

import com.alfarizi.techblog.constant.enumeration.CredentialTypeConstantEnum;

public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException (CredentialTypeConstantEnum type) {
        super("Invalid " + type);
    }
}
