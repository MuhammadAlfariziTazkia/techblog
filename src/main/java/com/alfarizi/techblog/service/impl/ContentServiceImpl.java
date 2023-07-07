package com.alfarizi.techblog.service.impl;

import javax.transaction.Transactional;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.service.intr.ContentService;
import com.alfarizi.techblog.service.intr.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.entity.Translation;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.ContentRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    @Qualifier("topicServiceImpl")
    private CoreService<Topic, TopicDto> topicService;

    @Autowired
    @Qualifier("translationServiceImpl")
    private CoreService<Translation, TranslationDto> translationService;

    @Autowired
    private CommonHelper commonHelper;

    @Override
    public Content create (ContentDto contentDto) {
        try {
            Topic topic = topicService.getById(contentDto.getTopicId());
            TranslationDto translationDto = TranslationDto.builder()
                    .english(contentDto.getEnglish())
                    .indonesian(contentDto.getIndonesian())
                    .build();
            Content content = Content.builder()
                    .topic(topic)
                    .translation(translationService.create(translationDto))
                    .createdAt(commonHelper.getCurrentTimestamp())
                    .build();
            return contentRepository.save(content);
        } catch (Exception e) {
            log.error("[create]-failed create content", e);
            throw new EntityFailedPersistException(PersistTypeEnum.CREATE, EntityTypeEnum.CONTENT);
        }
    }

    @Override
    public Content getById(String id) {
        return contentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.CONTENT));
    }

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content update(ContentDto contentDto, String id) {
        Content content = this.getById(id);
        Translation translation = translationService.update(
                TranslationDto.builder()
                        .english(contentDto.getEnglish())
                        .indonesian(contentDto.getIndonesian())
                        .build(),
                content.getTranslation().getId()
        );
        content.setTranslation(translation);

        if (contentDto.getTopicId() != null) {
            Topic topic = topicService.getById(contentDto.getTopicId());
            topic.setId(contentDto.getTopicId());
            content.setTopic(topic);
        }

        return contentRepository.save(content);
    }

    @Override
    public void delete(String id) {
        try {
            contentRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.CONTENT);
        }
    }

    @Override
    public Content getByTopicId(String topicId) {
        return contentRepository.findByTopicId(topicId);
    }
}
