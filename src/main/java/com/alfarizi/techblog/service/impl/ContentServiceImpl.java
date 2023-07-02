package com.alfarizi.techblog.service.impl;

import javax.transaction.Transactional;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.entity.Translation;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.intr.ContentService;
import com.alfarizi.techblog.service.intr.TopicService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private TopicService topicService;

    @Override
    public Content getContentByTopicId(String topicId) {
        return contentRepository.findById(topicId).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.TOPIC));
    }

    @Override
    public Content create (ContentDto contentDto) {
        Topic topic = topicService.getById(contentDto.getTopicId()).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.TOPIC));
        Translation translation = Translation.builder()
                .english(contentDto.getEnglish())
                .indonesian(contentDto.getIndonesian())
                .build();
        Content content = Content.builder()
                .topic(topic)
                .translation(translation)
                .createdAt(CommonHelper.getCurrentTimestamp())
                .build();
        return contentRepository.save(content);
    }

    @Override
    public void updateContent(String id, ContentDto contentDto) {
        throw new UnsupportedOperationException("Unimplemented method 'updateContent'");
    }

    @Override
    public void deleteContent(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteContent'");
    }

}
