package com.alfarizi.techblog.constant.variable;

import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.entity.Topic;

public class TopicTestVariable {
    public static final String TOPIC_NAME = "topic_name";
    public static final String TOPIC_DESCRIPTION = "topic_description";
    public static final String TOPIC_SUPERTOPICID = "super_topic_id";
    public static final String TOPIC_ID = "topic id";
    public static final String TOPIC_ID_UPDATED = "topic id 2";

    public static final Topic TOPIC = Topic.builder()
            .id(null)
            .name(TOPIC_NAME)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();

    public static final Topic TOPIC_WITH_ID = Topic.builder()
            .id(TOPIC_ID)
            .name(TOPIC_NAME)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();

    public static final Topic TOPIC_WITH_ID_UPDATED = Topic.builder()
            .id(TOPIC_ID_UPDATED)
            .name(TOPIC_NAME)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();

    public static final TopicDto TOPIC_DTO = TopicDto.builder()
            .name(TOPIC_NAME)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();

    public static final TopicDto TOPIC_DTO_WITHOUT_NAME = TopicDto.builder()
            .name(null)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();
}
