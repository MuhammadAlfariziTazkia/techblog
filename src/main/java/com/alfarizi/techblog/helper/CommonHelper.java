package com.alfarizi.techblog.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class CommonHelper {
    
    public Timestamp getCurrentTimestamp () {
        long currentTimeInMills = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeInMills);
        return new Timestamp(currentDate.getTime());
    }

    public URI getCreatedUri (String basePath, String id, UriComponentsBuilder uriComponentsBuilder) {
        UriComponents uriComponents = uriComponentsBuilder.path(basePath + "/{id}").buildAndExpand(id);
        return uriComponents.toUri();
    }
}
