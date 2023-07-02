package com.alfarizi.techblog.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class BasicAuthDto {
    
    @JsonProperty
    String token;
}
