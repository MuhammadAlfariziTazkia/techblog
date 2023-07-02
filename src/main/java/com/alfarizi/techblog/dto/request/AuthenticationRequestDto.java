package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;
}
