package com.alfarizi.techblog.constant.variable;

import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.entity.Content;

public class ContentTestVariable {

    public static final String CONTENT_ID = "content id";

    public static final Content CONTENT = Content.builder()
            .id(CONTENT_ID)
            .topicId(TopicTestVariable.TOPIC_ID)
            .translation(TranslationTestVariable.TRANSLATION)
            .build();

    public static final Content CONTENT_UPDATED_TRANSLATION_ONLY = Content.builder()
            .id(CONTENT_ID)
            .translation(TranslationTestVariable.TRANSLATION_UPDATED)
            .topicId(TopicTestVariable.TOPIC_ID)
            .build();

    public static final Content CONTENT_UPDATED_TOPIC_ONLY = Content.builder()
            .id(CONTENT_ID)
            .translation(TranslationTestVariable.TRANSLATION)
            .topicId(TopicTestVariable.TOPIC_ID_UPDATED)
            .build();

    public static final Content CONTENT_UPDATED_COMPLETE = Content.builder()
            .id(CONTENT_ID)
            .translation(TranslationTestVariable.TRANSLATION_UPDATED)
            .topicId(TopicTestVariable.TOPIC_ID_UPDATED)
            .build();

    public static final ContentDto CONTENT_DTO_UPDATE_ONLY_TRANSLATION = ContentDto.contentDtoBuilder()
            .indonesian(TranslationTestVariable.INDONESIAN_CONTENT_UPDATED)
            .english(TranslationTestVariable.ENGLISH_CONTENT_UPDATED)
            .build();

    public static final ContentDto CONTENT_DTO_UPDATE_ONLY_TOPIC = ContentDto.contentDtoBuilder()
            .topicId(TopicTestVariable.TOPIC_ID_UPDATED)
            .english(TranslationTestVariable.ENGLISH_CONTENT)
            .indonesian(TranslationTestVariable.INDONESIAN_CONTENT)
            .build();

    public static final ContentDto CONTENT_DTO_UPDATE_COMPLETE = ContentDto.contentDtoBuilder()
            .topicId(TopicTestVariable.TOPIC_ID_UPDATED)
            .english(TranslationTestVariable.ENGLISH_CONTENT_UPDATED)
            .indonesian(TranslationTestVariable.INDONESIAN_CONTENT_UPDATED)
            .build();
}
