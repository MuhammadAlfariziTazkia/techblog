package com.alfarizi.techblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfarizi.techblog.constant.variable.PathConstantVariable;
import com.alfarizi.techblog.dto.request.AuthenticationRequestDto;
import com.alfarizi.techblog.dto.response.BasicAuthDto;
import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.service.intr.AuthenticationService;
import com.alfarizi.techblog.util.JwtUtil;

@RestController
@RequestMapping(PathConstantVariable.BASE_API_PATH)
public class AuthenticationController {

    @Autowired
    private AuthenticationService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(PathConstantVariable.APPEND_LOGIN)
    public ResponseEntity<BasicResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        userService.authorize(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword());
        String token = jwtUtil.generateToken(authenticationRequestDto.getUsername());
        BasicAuthDto basicAuthDto = BasicAuthDto.builder().token(token).build();
        BasicResponseDto response = BasicResponseDto.builder()
                .status(HttpStatus.OK)
                .message("Success generate token")
                .data(basicAuthDto)
                .build();
        return ResponseEntity.ok().body(response);
    }
}
