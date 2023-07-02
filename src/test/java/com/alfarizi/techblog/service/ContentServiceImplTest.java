package com.alfarizi.techblog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.alfarizi.techblog.constant.variable.TestConstantVariable;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.impl.ContentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ContentServiceImplTest {

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private ContentServiceImpl contentService;

    @Test
    public void getContentByTopicIdTest () {
        // mock data
        when(contentRepository.findById(TestConstantVariable.TOPIC.getId())).thenReturn(Optional.of(TestConstantVariable.CONTENT));

        // perform
        Content result = contentService.getContentByTopicId(TestConstantVariable.TOPIC_ID);

        // assertions
        assertEquals(TestConstantVariable.CONTENT, result);
    }
}
