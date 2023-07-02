package com.alfarizi.techblog.constant.variable;

import java.util.UUID;

import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Topic;

public class TestConstantVariable {

    public static final String TOPIC_ID = "TOPIC-ID";

    public static final String CONTENT_ID = "CONTENT-ID";

    public static final Topic TOPIC = Topic.builder()
            .id(TOPIC_ID)
            .build();

    public static final Content CONTENT = Content.builder()
            .id(CONTENT_ID)
            .topic(TOPIC)
            .build();
}
