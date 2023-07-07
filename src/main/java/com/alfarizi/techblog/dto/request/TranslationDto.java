package com.alfarizi.techblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDto {

    @JsonProperty
    private String english;

    @JsonProperty
    private String indonesian;
}
