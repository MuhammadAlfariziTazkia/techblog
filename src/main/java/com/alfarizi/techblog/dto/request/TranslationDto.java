package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
public class TranslationDto {

    @JsonProperty
    private String indonesian;

    @JsonProperty
    private String english;

    @Builder(builderMethodName = "translationDtoBuilder")
    public TranslationDto (String indonesian, String english) {
        this.indonesian = indonesian;
        this.english = english;
    }
}
