package com.alfarizi.techblog.service;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.variable.ContentTestVariable;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.repository.ContentRepository;
import com.alfarizi.techblog.service.impl.ContentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    @Test
    public void getByIdTest () {
        // mock data
        when(contentRepository.findById(ContentTestVariable.CONTENT_ID)).thenReturn(Optional.of(ContentTestVariable.CONTENT_WITH_ID));

        // perform
        Content result = contentService.getById(ContentTestVariable.CONTENT_ID);

        // assertions
        assertEquals(ContentTestVariable.CONTENT_WITH_ID, result);

        // verify
        verify(contentRepository).findById(ContentTestVariable.CONTENT_ID);
    }

    @Test
    public void getByIdNotFoundTest () {
        when(contentRepository.findById(ContentTestVariable.CONTENT_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> contentService.getById(ContentTestVariable.CONTENT_ID));

        assertEquals(EntityTypeEnum.CONTENT, exception.getEntityType());

        verify(contentRepository).findById(ContentTestVariable.CONTENT_ID);
    }
}
