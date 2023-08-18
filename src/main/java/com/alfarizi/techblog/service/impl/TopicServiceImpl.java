package com.alfarizi.techblog.service.impl;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.TopicRepository;
import com.alfarizi.techblog.service.intr.ContentService;
import com.alfarizi.techblog.service.intr.CoreService;
import com.alfarizi.techblog.service.intr.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TopicServiceImpl implements CoreService<Topic, TopicDto> {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private CommonHelper commonHelper;

    @Override
    public Topic create(TopicDto topicDto) {
        try {
            Topic topic = Topic.builder()
                    .createdAt(commonHelper.getCurrentTimestamp())
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
    public Topic getById(String id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.TOPIC));
        topic.setSubTopics(topicRepository.findBySuperTopicId(id));
        topic.setContent(contentService.getByTopicId(id));
        topic.setReferences(referenceService.getByTopicId(id));
        return topic;
    }

    @Override
    public Topic update(TopicDto topicDto, String id) {
        try {
            Topic topic = this.getById(id);
            if (topicDto.getSuperTopicId() != null) topic.setSuperTopicId(topicDto.getSuperTopicId());
            if (topicDto.getDescription() != null) topic.setDescription(topicDto.getDescription());
            if (topicDto.getName() != null) topic.setName(topicDto.getName());
            topicRepository.save(topic);
            return topic;
        } catch (EntityNotFoundException e) {
            log.error("[update]-failed update topic {}", e.getMessage());
            throw e;
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
