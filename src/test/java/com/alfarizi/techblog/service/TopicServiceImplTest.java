package com.alfarizi.techblog.service;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.TopicRepository;
import com.alfarizi.techblog.service.impl.TopicServiceImpl;
import com.alfarizi.techblog.service.intr.ContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.alfarizi.techblog.constant.variable.TopicTestVariable.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private CommonHelper commonHelper;

    @Mock
    private ContentService contentService;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    public void createSuccessTest () {
        when(commonHelper.getCurrentTimestamp()).thenReturn(null);
        when(topicRepository.save(TOPIC)).thenReturn(TOPIC);

        Topic actualTopic = topicService.create(TOPIC_DTO);

        assertEquals(TOPIC, actualTopic);

        verify(topicRepository).save(TOPIC);
    }

    @Test
    public void createFailedTest () {
        when(commonHelper.getCurrentTimestamp()).thenReturn(null);
        when(topicRepository.save(TOPIC)).thenThrow(EntityFailedPersistException.class);

        EntityFailedPersistException exception = assertThrows(
                EntityFailedPersistException.class,
                () -> topicService.create(TOPIC_DTO)
        );

        assertEquals(EntityTypeEnum.TOPIC, exception.getEntityType());

        verify(commonHelper).getCurrentTimestamp();
        verify(topicRepository).save(TOPIC);
    }

    @Test
    public void getByIdSuccessTest () {
        when(topicRepository.findById(TOPIC_ID)).thenReturn(Optional.of(TOPIC_WITH_ID));
        when(topicRepository.findBySuperTopicId(TOPIC_ID)).thenReturn(Collections.emptyList());
        when(contentService.getByTopicId(TOPIC_ID)).thenReturn(null);

        Topic actualTopic = topicService.getById(TOPIC_ID);

        assertEquals(TOPIC_WITH_ID, actualTopic);

        verify(topicRepository).findById(TOPIC_ID);
        verify(topicRepository).findBySuperTopicId(TOPIC_ID);
        verify(contentService).getByTopicId(TOPIC_ID);
    }

    @Test
    public void getByIdNotFoundTest () {
        when(topicRepository.findById(TOPIC_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> topicService.getById(TOPIC_ID)
        );

        assertEquals(EntityTypeEnum.TOPIC, exception.getEntityType());

        verify(topicRepository).findById(TOPIC_ID);
    }

    @Test
    public void updateSuccessTest () {
        when(topicRepository.findById(TOPIC_ID)).thenReturn(Optional.of(TOPIC_WITH_ID));
        when(topicRepository.findBySuperTopicId(TOPIC_ID)).thenReturn(Collections.emptyList());
        when(contentService.getByTopicId(TOPIC_ID)).thenReturn(null);
        when(topicRepository.save(TOPIC_WITH_ID)).thenReturn(TOPIC_WITH_ID);

        Topic actual = topicService.update(TOPIC_DTO, TOPIC_ID);

        assertEquals(TOPIC_WITH_ID, actual);

        verify(topicRepository).findById(TOPIC_ID);
        verify(topicRepository).findBySuperTopicId(TOPIC_ID);
        verify(contentService).getByTopicId(TOPIC_ID);
        verify(topicRepository).save(TOPIC_WITH_ID);
    }

    @Test
    public void updateTopicNotFoundTest () {
        when(topicRepository.findById(TOPIC_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> topicService.update(TOPIC_DTO, TOPIC_ID)
        );

        assertEquals(EntityTypeEnum.TOPIC, exception.getEntityType());

        verify(topicRepository).findById(TOPIC_ID);
    }

    @Test
    public void updateTopicFailedTest () {
        when(topicRepository.findById(TOPIC_ID)).thenReturn(Optional.of(TOPIC_WITH_ID));
        when(topicRepository.findBySuperTopicId(TOPIC_ID)).thenReturn(Collections.emptyList());
        when(contentService.getByTopicId(TOPIC_ID)).thenReturn(null);
        when(topicRepository.save(TOPIC_WITH_ID)).thenThrow(EntityFailedPersistException.class);

        EntityFailedPersistException exception = assertThrows(
                EntityFailedPersistException.class,
                () -> topicService.update(TOPIC_DTO, TOPIC_ID)
        );

        assertEquals(EntityTypeEnum.TOPIC, exception.getEntityType());

        verify(topicRepository).findById(TOPIC_ID);
        verify(topicRepository).findBySuperTopicId(TOPIC_ID);
        verify(contentService).getByTopicId(TOPIC_ID);
        verify(topicRepository).save(TOPIC_WITH_ID);
    }

    @Test
    public void deleteSuccessTest () {
        doNothing().when(topicRepository).deleteById(TOPIC_ID);

        topicService.delete(TOPIC_ID);

        verify(topicRepository).deleteById(TOPIC_ID);
    }

    @Test
    public void deleteFailedTest() {
        doThrow(EntityFailedPersistException.class).when(topicRepository).deleteById(TOPIC_ID);

        EntityFailedPersistException exception = assertThrows(
                EntityFailedPersistException.class,
                () -> topicService.delete(TOPIC_ID)
        );

        assertEquals(EntityTypeEnum.TOPIC, exception.getEntityType());

        verify(topicRepository).deleteById(TOPIC_ID);
    }
}
