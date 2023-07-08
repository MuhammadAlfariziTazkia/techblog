package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContentDto extends TranslationDto{

    @JsonProperty
    private String topicId;

    @Builder(builderMethodName = "contentDtoBuilder")
    public ContentDto (String indonesian, String english, String topicId) {
        super(indonesian, english);
        this.topicId = topicId;
    }
}
