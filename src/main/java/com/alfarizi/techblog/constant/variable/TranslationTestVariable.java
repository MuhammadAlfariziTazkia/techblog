package com.alfarizi.techblog.constant.variable;

import com.alfarizi.techblog.entity.Translation;

public class TranslationTestVariable {

    public static final String ENGLISH_CONTENT = "english content";
    public static final String INDONESIAN_CONTENT = "indonesian content";

    public static final Translation TRANSLATION = Translation
            .builder()
            .english(ENGLISH_CONTENT)
            .indonesian(INDONESIAN_CONTENT)
            .build();
}
