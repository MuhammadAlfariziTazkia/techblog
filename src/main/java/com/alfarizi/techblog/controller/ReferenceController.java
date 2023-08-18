package com.alfarizi.techblog.controller;

import com.alfarizi.techblog.constant.variable.PathConstantVariable;
import com.alfarizi.techblog.dto.request.ReferenceDto;
import com.alfarizi.techblog.dto.response.BasicResponseDto;
import com.alfarizi.techblog.entity.Reference;
import com.alfarizi.techblog.helper.CommonHelper;
import com.alfarizi.techblog.service.intr.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(PathConstantVariable.BASE_PRIVATE_REFERENCE_PATH)
public class ReferenceController {

    @Autowired
    @Qualifier("referenceServiceImpl")
    private CoreService<Reference, ReferenceDto> referenceService;

    @Autowired
    private CommonHelper commonHelper;

    @PostMapping
    public ResponseEntity<BasicResponseDto> createReference (@RequestBody ReferenceDto referenceDto, UriComponentsBuilder uriComponentsBuilder) {

        Reference reference = referenceService.create(referenceDto);
        return ResponseEntity.created(
                        commonHelper.getCreatedUri(
                                PathConstantVariable.BASE_PRIVATE_CONTENT_PATH,
                                reference.getId(),
                                uriComponentsBuilder))
                .body(
                        BasicResponseDto.builder()
                                .status(HttpStatus.CREATED)
                                .message("Success create reference")
                                .data(reference)
                                .build());
    }

    @PutMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> updateReference (@PathVariable String id, @RequestBody ReferenceDto referenceDto) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(referenceService.update(referenceDto, id))
                        .status(HttpStatus.OK)
                        .message("Success update reference")
                        .build()
        );
    }

    @DeleteMapping(PathConstantVariable.APPEND_ID)
    public ResponseEntity<BasicResponseDto> deleteReference (@PathVariable String id) {
        referenceService.delete(id);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .data(null)
                        .message("Success delete reference")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
