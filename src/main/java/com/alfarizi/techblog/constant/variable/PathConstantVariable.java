package com.alfarizi.techblog.constant.variable;

public class PathConstantVariable {
    
    public final static String BASE_API_PATH = "/api";
    public final static String BASE_PUBLIC_PATH = BASE_API_PATH + "/public";
    public final static String BASE_PRIVATE_PATH = BASE_API_PATH + "/private";

    public final static String APPEND_LOGIN = "/login";
    public final static String APPEND_CONTENT = "/content";
    public final static String APPEND_TOPIC = "/topic";

    public final static String BASE_PRIVATE_CONTENT_PATH = BASE_PRIVATE_PATH + APPEND_CONTENT;
    public final static String BASE_PRIVATE_TOPIC_PATH = BASE_PRIVATE_PATH + APPEND_TOPIC;
}
