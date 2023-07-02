package com.alfarizi.techblog.service.impl;

import java.util.List;
import java.util.Optional;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.TopicRepository;
import com.alfarizi.techblog.service.intr.TopicService;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic create(TopicDto topicDto) {
        try {
            Topic topic = Topic.builder()
                    .createdAt(CommonHelper.getCurrentTimestamp())
                    .description(topicDto.getDescription())
                    .name(topicDto.getName())
                    .superTopicId(topicDto.getSuperTopicId())
                    .build();

            return topicRepository.save(topic);
        } catch (Exception e) {
            log.error("[create]-failed create topic", e);
            throw new EntityFailedPersistException(PersistTypeEnum.CREATE, EntityTypeEnum.TOPIC);
        }
    }

    @Override
    public Optional<Topic> getById(String id) {
        return topicRepository.findById(id);
    }

    @Override
    public Topic update(TopicDto topicDto, String id) {
        try {
            Topic topic = this.getById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.TOPIC));
            if (topicDto.getSuperTopicId() != null) topic.setSuperTopicId(topicDto.getSuperTopicId());
            if (topicDto.getDescription() != null) topic.setDescription(topicDto.getDescription());
            if (topicDto.getName() != null) topic.setName(topicDto.getName());
            topicRepository.save(topic);
            return topic;
        } catch (Exception e) {
            log.error("[update]-failed update topic", e);
            throw new EntityFailedPersistException(PersistTypeEnum.UPDATE, EntityTypeEnum.TOPIC);
        }
    }

    @Override
    public void delete(String id) {
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.TOPIC);
        }
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }
}
