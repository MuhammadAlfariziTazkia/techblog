package com.alfarizi.techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<BasicResponseDto> createTopic(@RequestBody TopicDto topicDto,
            UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicService.create(topicDto);
        return ResponseEntity.created(
                CommonHelper.getCreatedUri(PathConstantVariable.BASE_PRIVATE_TOPIC_PATH, topic.getId().toString(),
                        uriComponentsBuilder))
                .body(
                        BasicResponseDto.builder()
                                .data(topic)
                                .message("Success create topic")
                                .status(HttpStatus.CREATED)
                                .build());
    }
}
