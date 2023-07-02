package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContentDto {

    @JsonProperty
    private String topicId;

    @JsonProperty
    private String english;

    @JsonProperty
    private String indonesian;
}
