package com.alfarizi.techblog.controller;

import com.alfarizi.techblog.service.intr.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alfarizi.techblog.constant.variable.PathConstantVariable;
import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.helper.CommonHelper;

@RestController
@RequestMapping(PathConstantVariable.BASE_PRIVATE_CONTENT_PATH)
public class ContentController {

    @Autowired
    @Qualifier("contentServiceImpl")
    private CoreService<Content, ContentDto> contentService;

    @Autowired
    private CommonHelper commonHelper;

    @PostMapping
    public ResponseEntity<BasicResponseDto> createContent(@RequestBody ContentDto contentDto,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Content content = contentService.create(contentDto);
        return ResponseEntity.created(
                commonHelper.getCreatedUri(
                        PathConstantVariable.BASE_PRIVATE_CONTENT_PATH,
                        content.getId(),
                        uriComponentsBuilder))
                .body(
                        BasicResponseDto.builder()
                                .status(HttpStatus.CREATED)
                                .message("Success create content")
                                .data(content)
                                .build());
    }
}
