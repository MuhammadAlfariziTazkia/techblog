package com.alfarizi.techblog.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicResponseDto {

    @JsonProperty
    HttpStatus status;

    @JsonProperty
    String message;

    @JsonProperty
    Object data;
}
