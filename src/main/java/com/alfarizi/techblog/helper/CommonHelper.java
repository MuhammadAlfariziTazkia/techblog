package com.alfarizi.techblog.helper;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class CommonHelper {
    
    public static Timestamp getCurrentTimestamp () {
        Long currentTimeInMills = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMills);
        return new Timestamp(currentDate.getTime());
    }

    public static URI getCreatedUri (String basePath, String id, UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents = uriComponentsBuilder.path(basePath + "/{id}").buildAndExpand(id);
        return uriComponents.toUri();
    }
}
