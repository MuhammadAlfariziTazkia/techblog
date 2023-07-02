package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicDto {
    
    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String superTopicId;
}
