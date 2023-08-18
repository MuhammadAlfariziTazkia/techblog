package com.alfarizi.techblog.dto.request;

import com.alfarizi.techblog.constant.enumeration.LinkTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferenceDto {

    @JsonProperty
    String name;

    @JsonProperty
    String topicId;

    @JsonProperty
    String url;

    @JsonProperty
    LinkTypeEnum linkType;
}
