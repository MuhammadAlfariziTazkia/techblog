package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TopicDto {
    
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String superTopicId;
}
