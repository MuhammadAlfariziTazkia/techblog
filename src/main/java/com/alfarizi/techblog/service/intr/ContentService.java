package com.alfarizi.techblog.service.intr;

import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.entity.Content;

public interface ContentService extends CoreService<Content, ContentDto>{

    Content getByTopicId(String topicId);
}
