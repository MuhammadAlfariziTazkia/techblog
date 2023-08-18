package com.alfarizi.techblog.service.impl;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.LinkDto;
import com.alfarizi.techblog.entity.Link;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.repository.LinkRepository;
import com.alfarizi.techblog.service.intr.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public Link create(LinkDto dto) {
        try {
            Link link = Link.builder()
                    .url(dto.getUrl())
                    .type(dto.getType())
                    .build();

            return linkRepository.save(link);
        } catch (Exception e) {
            log.error("[create]-failed create link", e);
            throw new EntityFailedPersistException(PersistTypeEnum.CREATE, EntityTypeEnum.LINK);
        }
    }

    @Override
    public Link getById(String id) {
        return linkRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.LINK));
    }

    @Override
    public List<Link> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Link update(LinkDto dto, String id) {
        try {
            Link link = this.getById(id);
            if (!Objects.isNull(dto.getUrl())) link.setUrl(dto.getUrl());
            if (!Objects.isNull(dto.getType())) link.setType(dto.getType());
            return linkRepository.save(link);
        } catch (Exception e) {
            log.error("[update]-failed update link", e);
            throw new EntityFailedPersistException(PersistTypeEnum.UPDATE, EntityTypeEnum.LINK);
        }
    }

    @Override
    public void delete(String id) {
        try {
            linkRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[delete]-failed delete link", e);
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.LINK);
        }
    }
}
