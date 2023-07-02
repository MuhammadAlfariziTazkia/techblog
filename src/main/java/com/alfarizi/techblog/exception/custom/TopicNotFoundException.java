package com.alfarizi.techblog.exception.custom;

public class TopicNotFoundException extends RuntimeException {
    
    public TopicNotFoundException () {
        super("Topic not found");
    }
}
