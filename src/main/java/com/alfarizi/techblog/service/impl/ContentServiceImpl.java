package com.alfarizi.techblog.service.impl;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Translation;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.intr.ContentService;
import com.alfarizi.techblog.service.intr.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    @Qualifier("translationServiceImpl")
    private CoreService<Translation, TranslationDto> translationService;

    @Autowired
    private CommonHelper commonHelper;

    @Override
    public Content create (ContentDto contentDto) {
        try {
            TranslationDto translationDto = TranslationDto.translationDtoBuilder()
                    .english(contentDto.getEnglish())
                    .indonesian(contentDto.getIndonesian())
                    .build();
            Content content = Content.builder()
                    .topicId(contentDto.getTopicId())
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
        try {
            Content content = this.getById(id);
            Translation translation = translationService.update(
                    TranslationDto.translationDtoBuilder()
                            .english(contentDto.getEnglish())
                            .indonesian(contentDto.getIndonesian())
                            .build(),
                    content.getTranslation().getId()
            );
            content.setTranslation(translation);

            if (contentDto.getTopicId() != null) content.setTopicId(contentDto.getTopicId());
            return contentRepository.save(content);
        } catch (EntityNotFoundException e) {
            log.error("[update]-failed update topic: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("[update]-failed update topic", e);
            throw new EntityFailedPersistException(PersistTypeEnum.UPDATE, EntityTypeEnum.CONTENT);
        }
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
