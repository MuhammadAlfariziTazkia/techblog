package com.alfarizi.techblog.service.intr;

import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.entity.Content;

public interface ContentService {

    Content getContentByTopicId (String topicId);

    Content create (ContentDto contentDto);

    void updateContent(String id, ContentDto contentDto);

    void deleteContent(String id);
}
