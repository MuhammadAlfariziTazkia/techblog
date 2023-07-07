package com.alfarizi.techblog.controller;

import com.alfarizi.techblog.constant.variable.PathConstantVariable;
import com.alfarizi.techblog.dto.request.ContentDto;
import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.entity.Content;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.service.intr.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PathConstantVariable.BASE_PRIVATE_CONTENT_PATH)
public class ContentController {

    @Autowired
    private ContentService contentService;

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

    @GetMapping
    public ResponseEntity<BasicResponseDto> getAllContent() {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .status(HttpStatus.OK)
                        .message("Success generate all content")
                        .data(contentService.getAll())
                        .build()
        );
    }

    @GetMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> getContentById(@PathVariable String id) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(contentService.getById(id))
                        .message("Success generate content")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PutMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> updateContentById (@RequestBody ContentDto contentDto, @PathVariable String id) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(contentService.update(contentDto, id))
                        .status(HttpStatus.OK)
                        .message("Success update content")
                        .build()
        );
    }

    @DeleteMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> deleteContentById (@PathVariable String id) {
        contentService.delete(id);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(null)
                        .message("Success delete content")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
