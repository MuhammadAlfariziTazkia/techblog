package com.alfarizi.techblog.constant.variable;

import java.util.UUID;

import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Topic;

public class TestConstantVariable {

    public static final String TOPIC_ID = "topic_id";
    public static final String TOPIC_NAME = "topic_name";
    public static final String TOPIC_DESCRIPTION = "topic_description";
    public static final String TOPIC_SUPERTOPICID = "super_topic_id";

    public static final String CONTENT_ID = "CONTENT-ID";

    public static final Topic TOPIC = Topic.builder()
            .id(TOPIC_ID)
            .name(TOPIC_NAME)
            .description(TOPIC_DESCRIPTION)
            .superTopicId(TOPIC_SUPERTOPICID)
            .build();


    public static final Content CONTENT = Content.builder()
            .id(CONTENT_ID)
            .topic(TOPIC)
            .build();
}
