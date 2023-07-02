package com.alfarizi.techblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.alfarizi.techblog.constant.variable.TopicTestVariable;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.repository.TopicRepository;
import com.alfarizi.techblog.service.impl.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.alfarizi.techblog.constant.variable.TestConstantVariable;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.impl.ContentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    public void createSuccessTest () {
        Topic topic = TopicTestVariable.TOPIC;
        topic.setCreatedAt(any());

        when(topicRepository.save(topic)).thenReturn(TopicTestVariable.TOPIC);
        Topic actualTopic = topicService.create(TopicTestVariable.TOPIC_DTO);

        assertEquals(TopicTestVariable.TOPIC, actualTopic);

        ArgumentCaptor<Topic> captor = ArgumentCaptor.forClass(Topic.class);
        verify(topicRepository).save(captor.capture());
    }
}
