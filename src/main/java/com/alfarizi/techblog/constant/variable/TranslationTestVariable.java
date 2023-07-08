package com.alfarizi.techblog.constant.variable;

import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.entity.Translation;

public class TranslationTestVariable {

    public static final String ENGLISH_CONTENT = "english content";
    public static final String ENGLISH_CONTENT_UPDATED = "konten english";
    public static final String INDONESIAN_CONTENT = "indonesian content";
    public static final String INDONESIAN_CONTENT_UPDATED = "konten indonesia";
    public static final String TRANSLATION_ID = "translation id";

    public static final Translation TRANSLATION = Translation
            .builder()
            .id(TRANSLATION_ID)
            .english(ENGLISH_CONTENT)
            .indonesian(INDONESIAN_CONTENT)
            .build();

    public static final Translation TRANSLATION_UPDATED = Translation
            .builder()
            .id(TRANSLATION_ID)
            .english(ENGLISH_CONTENT_UPDATED)
            .indonesian(INDONESIAN_CONTENT_UPDATED)
            .build();
    public static final TranslationDto TRANSLATION_DTO = TranslationDto
            .translationDtoBuilder()
            .english(ENGLISH_CONTENT)
            .indonesian(INDONESIAN_CONTENT)
            .build();

    public static final TranslationDto TRANSLATION_DTO_UPDATED = TranslationDto
            .translationDtoBuilder()
            .english(ENGLISH_CONTENT_UPDATED)
            .indonesian(INDONESIAN_CONTENT_UPDATED)
            .build();
}
