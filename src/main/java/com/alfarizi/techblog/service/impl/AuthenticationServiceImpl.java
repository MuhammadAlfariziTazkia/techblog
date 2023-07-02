package com.alfarizi.techblog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alfarizi.techblog.constant.enumeration.CredentialTypeConstantEnum;
import com.alfarizi.techblog.exception.custom.InvalidCredentialException;
import com.alfarizi.techblog.service.intr.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${credential.username}")
    private String username;

    @Value("${credential.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(this.username)) throw new UsernameNotFoundException("Invalid username");
        return new User(username, password, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }

    @Override
    public void authorize(String username, String password) throws InvalidCredentialException {
        if (!this.username.equals(username)) throw new InvalidCredentialException(CredentialTypeConstantEnum.USERNAME);
        if (!this.password.equals(password)) throw new InvalidCredentialException(CredentialTypeConstantEnum.PASSWORD);
    }

    
}
