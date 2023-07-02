package com.alfarizi.techblog.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.TopicRepository;
import com.alfarizi.techblog.service.intr.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic create(TopicDto topicDto) {
        UUID superTopicid = topicDto.getSuperTopicId() != null
                ? UUID.fromString(topicDto.getSuperTopicId())
                : null;
        Topic topic = Topic.builder()
                .createdAt(CommonHelper.getCurrentTimestamp())
                .description(topicDto.getDescription())
                .name(topicDto.getName())
                .superTopicId(superTopicid)
                .build();
        return topicRepository.save(topic);
    }

    @Override
    public Optional<Topic> findById(String id) {
        return topicRepository.findById(id);
    }
}
