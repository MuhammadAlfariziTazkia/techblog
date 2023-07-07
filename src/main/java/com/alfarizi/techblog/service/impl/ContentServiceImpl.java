package com.alfarizi.techblog.service.impl;

import javax.transaction.Transactional;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.service.intr.CoreService;
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

@Service
@Transactional
public class ContentServiceImpl implements CoreService<Content, ContentDto> {

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
        Topic topic = topicService.getById(contentDto.getTopicId());
        Translation translation = Translation.builder()
                .english(contentDto.getEnglish())
                .indonesian(contentDto.getIndonesian())
                .build();
        Content content = Content.builder()
                .topic(topic)
                .translation(translation)
                .createdAt(commonHelper.getCurrentTimestamp())
                .build();
        return contentRepository.save(content);
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
        return contentRepository.save(content);
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }
}
