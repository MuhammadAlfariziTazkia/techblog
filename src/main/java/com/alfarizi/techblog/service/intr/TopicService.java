package com.alfarizi.techblog.service.intr;

import java.util.List;
import java.util.Optional;

import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.entity.Topic;

public interface TopicService {
    
    Topic create (TopicDto topicDto);

    Optional<Topic> getById(String id);

    Topic update (TopicDto topicDto, String id);

    void delete (String id);

    List<Topic> getAll ();
}
