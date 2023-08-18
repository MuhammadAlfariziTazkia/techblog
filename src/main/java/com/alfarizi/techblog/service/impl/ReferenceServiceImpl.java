package com.alfarizi.techblog.service.impl;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.LinkDto;
import com.alfarizi.techblog.dto.request.ReferenceDto;
import com.alfarizi.techblog.entity.Link;
import com.alfarizi.techblog.entity.Reference;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.repository.ReferenceRepository;
import com.alfarizi.techblog.service.intr.CoreService;
import com.alfarizi.techblog.service.intr.ReferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    @Qualifier("linkServiceImpl")
    private CoreService<Link, LinkDto> linkService;

    @Override
    public Reference create(ReferenceDto dto) {
        try {
            LinkDto linkDto = LinkDto.builder()
                    .url(dto.getUrl())
                    .type(dto.getLinkType())
                    .build();

            Link link = linkService.create(linkDto);
            Reference reference = Reference.builder()
                    .name(dto.getName())
                    .link(link)
                    .topicId(dto.getTopicId())
                    .build();

            return referenceRepository.save(reference);
        } catch (Exception e) {
            log.error("[create]-failed create reference", e);
            throw new EntityFailedPersistException(PersistTypeEnum.CREATE, EntityTypeEnum.REFERENCE);
        }
    }

    @Override
    public Reference getById(String id) {
        return referenceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.REFERENCE));
    }

    @Override
    public List<Reference> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reference update(ReferenceDto dto, String id) {
        try {
            Reference reference = this.getById(id);
            if (!Objects.isNull(dto.getName())) reference.setName(dto.getName());
            if (!Objects.isNull(dto.getUrl()) || !Objects.isNull(dto.getLinkType())) {
                LinkDto linkDto = new LinkDto();
                if (!Objects.isNull(dto.getUrl())) linkDto.setUrl(dto.getUrl());
                if (!Objects.isNull(dto.getLinkType())) linkDto.setType(dto.getLinkType());
                linkService.update(linkDto, reference.getLink().getId());
            }
            return referenceRepository.save(reference);
        } catch (Exception e) {
            log.error("[create]-failed update reference", e);
            throw new EntityFailedPersistException(PersistTypeEnum.UPDATE, EntityTypeEnum.REFERENCE);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Reference reference = this.getById(id);
            if (!Objects.isNull(reference.getLink()) && !Objects.isNull(reference.getLink().getId())) {
                linkService.delete(reference.getLink().getId());
            }
            referenceRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[create]-failed delete reference", e);
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.REFERENCE);
        }
    }

    @Override
    public List<Reference> getByTopicId(String topicId) {
        return referenceRepository.findByTopicId(topicId);
    }
}
