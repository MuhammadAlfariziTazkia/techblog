package com.alfarizi.techblog.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.exception.custom.InvalidCredentialException;
import com.alfarizi.techblog.exception.custom.TopicNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<BasicResponseDto> handleUsernameNotFoundException() {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(
                                                BasicResponseDto.builder()
                                                                .message("Username not found")
                                                                .status(HttpStatus.UNAUTHORIZED)
                                                                .data(null)
                                                                .build());
        }

        @ExceptionHandler(InvalidCredentialException.class)
        public ResponseEntity<BasicResponseDto> handleInvalidCredentialException(InvalidCredentialException exception) {
                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(
                                                BasicResponseDto.builder()
                                                                .message(exception.getMessage())
                                                                .data(null)
                                                                .build());
        }

        @ExceptionHandler(TopicNotFoundException.class)
        public ResponseEntity<BasicResponseDto> handleTopicNotFoundException() {
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(
                                                BasicResponseDto.builder()
                                                                .message("Topic not found")
                                                                .data(null)
                                                                .build());
        }
}
