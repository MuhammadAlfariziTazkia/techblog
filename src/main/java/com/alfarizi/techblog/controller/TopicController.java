package com.alfarizi.techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.alfarizi.techblog.constant.variable.PathConstantVariable;
import com.alfarizi.techblog.dto.request.TopicDto;
import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.entity.Topic;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.service.intr.TopicService;

@RestController
@RequestMapping(PathConstantVariable.BASE_PRIVATE_TOPIC_PATH)
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<BasicResponseDto> create (@RequestBody TopicDto topicDto,
            UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicService.create(topicDto);
        return ResponseEntity.created(
                CommonHelper.getCreatedUri(PathConstantVariable.BASE_PRIVATE_TOPIC_PATH, topic.getId(),
                        uriComponentsBuilder))
                .body(
                        BasicResponseDto.builder()
                                .data(topic)
                                .message("Success create topic")
                                .status(HttpStatus.CREATED)
                                .build());
    }

    @PutMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> update (@RequestBody TopicDto topicDto, @PathVariable String id) {
        Topic topic = topicService.update(topicDto, id);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(topic)
                        .message("success update topic")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BasicResponseDto> getAll () {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(topicService.getAll())
                        .message("success get all topic")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @GetMapping (PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> getById (@PathVariable String id) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(topicService.getById(id))
                        .message("success get topic")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @DeleteMapping (PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> deleteById (@PathVariable String id){
        topicService.delete(id);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .message("success delete topic")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
