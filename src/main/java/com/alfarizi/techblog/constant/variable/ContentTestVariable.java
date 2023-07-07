package com.alfarizi.techblog.constant.variable;

import com.alfarizi.techblog.entity.Content;

public class ContentTestVariable {

    public static final String CONTENT_ID = "content id";

    public static final Content CONTENT = Content.builder()
            .topic(TopicTestVariable.TOPIC)
            .translation(TranslationTestVariable.TRANSLATION)
            .build();

    public static final Content CONTENT_WITH_TOPIC_ID = Content.builder()
            .topic(TopicTestVariable.TOPIC_WITH_ID)
            .translation(TranslationTestVariable.TRANSLATION)
            .build();

    public static final Content CONTENT_WITH_ID = Content.builder()
            .id(CONTENT_ID)
            .topic(TopicTestVariable.TOPIC)
            .translation(TranslationTestVariable.TRANSLATION)
            .build();
}
