package com.alfarizi.techblog.service;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.variable.TranslationTestVariable;
import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.entity.Translation;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.impl.ContentServiceImpl;
import com.alfarizi.techblog.service.intr.CoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

import static com.alfarizi.techblog.constant.variable.ContentTestVariable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContentServiceImplTest {

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private ContentServiceImpl contentService;

    @Mock
    @Qualifier("translationServiceImpl")
    private CoreService<Translation, TranslationDto> translationService;

    @Mock
    @Qualifier("topicServiceImpl")
    private CoreService<Topic, TopicDto> topicService;

    @Test
    public void getByIdTest () {
        // mock data
        when(contentRepository.findById(CONTENT_ID)).thenReturn(Optional.of(CONTENT));

        // perform
        Content result = contentService.getById(CONTENT_ID);

        // assertions
        assertEquals(CONTENT, result);

        // verify
        verify(contentRepository).findById(CONTENT_ID);
    }

    @Test
    public void getByIdNotFoundTest () {
        when(contentRepository.findById(CONTENT_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> contentService.getById(CONTENT_ID));

        assertEquals(EntityTypeEnum.CONTENT, exception.getEntityType());

        verify(contentRepository).findById(CONTENT_ID);
    }

    @Test
    public void getAllTest () {
        when(contentRepository.findAll()).thenReturn(List.of(CONTENT));

        List<Content> results = contentService.getAll();

        assertEquals(List.of(CONTENT), results);

        verify(contentRepository).findAll();
    }

    @Test
    public void updateContentTranslationOnlySuccessTest () {
        when(contentRepository.findById(CONTENT_ID)).thenReturn(Optional.of(CONTENT));
        when(translationService.update(TranslationTestVariable.TRANSLATION_DTO_UPDATED, CONTENT.getTranslation().getId()))
                .thenReturn(TranslationTestVariable.TRANSLATION_UPDATED);
        when(contentRepository.save(CONTENT_UPDATED_TRANSLATION_ONLY)).thenReturn(CONTENT_UPDATED_TRANSLATION_ONLY);

        Content updatedContent = contentService.update(CONTENT_DTO_UPDATE_ONLY_TRANSLATION, CONTENT_ID);

        assertEquals(CONTENT_UPDATED_TRANSLATION_ONLY, updatedContent);

        verify(contentRepository).findById(CONTENT_ID);
        verify(translationService).update(TranslationTestVariable.TRANSLATION_DTO_UPDATED, CONTENT.getTranslation().getId());
        verify(contentRepository).save(CONTENT_UPDATED_TRANSLATION_ONLY);
    }

    @Test
    public void updateContentTopicOnlySuccessTest () {
        when(contentRepository.findById(CONTENT_ID)).thenReturn(Optional.of(CONTENT));
        when(translationService.update(TranslationTestVariable.TRANSLATION_DTO, CONTENT.getTranslation().getId()))
                .thenReturn(TranslationTestVariable.TRANSLATION);
        when(contentRepository.save(CONTENT_UPDATED_TOPIC_ONLY)).thenReturn(CONTENT_UPDATED_TOPIC_ONLY);

        Content updatedContent = contentService.update(CONTENT_DTO_UPDATE_ONLY_TOPIC, CONTENT_ID);

        assertEquals(CONTENT_UPDATED_TOPIC_ONLY, updatedContent);

        verify(contentRepository).findById(CONTENT_ID);
        verify(translationService).update(TranslationTestVariable.TRANSLATION_DTO, CONTENT.getTranslation().getId());
        verify(contentRepository).save(CONTENT_UPDATED_TOPIC_ONLY);
    }
}
