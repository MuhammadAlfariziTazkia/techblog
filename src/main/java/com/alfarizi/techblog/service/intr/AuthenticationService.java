package com.alfarizi.techblog.service.intr;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.alfarizi.techblog.exception.custom.InvalidCredentialException;

public interface AuthenticationService extends UserDetailsService {

    void authorize (String username, String password) throws InvalidCredentialException;
}
