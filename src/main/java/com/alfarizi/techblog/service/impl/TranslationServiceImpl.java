package com.alfarizi.techblog.service.impl;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import com.alfarizi.techblog.dto.request.TranslationDto;
import com.alfarizi.techblog.entity.Translation;
import com.alfarizi.techblog.exception.custom.EntityFailedPersistException;
import com.alfarizi.techblog.exception.custom.EntityNotFoundException;
import com.alfarizi.techblog.repository.TranslationRepository;
import com.alfarizi.techblog.service.intr.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TranslationServiceImpl implements CoreService<Translation, TranslationDto> {

    @Autowired
    private TranslationRepository translationRepository;

    @Override
    public Translation create(TranslationDto dto) {
        return null;
    }

    @Override
    public Translation getById(String id) {
        return translationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityTypeEnum.TRANSLATION));
    }

    @Override
    public List<Translation> getAll() {
        return null;
    }

    @Override
    public Translation update(TranslationDto translationDto, String id) {
        try {
            Translation translation = this.getById(id);
            translation.setIndonesian(translationDto.getIndonesian() != null ? translationDto.getIndonesian() : translation.getIndonesian());
            translation.setEnglish(translationDto.getEnglish() != null ? translationDto.getEnglish() : translation.getEnglish());
            return translationRepository.save(translation);
        } catch (EntityNotFoundException e) {
            log.error("[update]-failed update topic: {}", e.getMessage());
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.TRANSLATION);
        } catch (Exception e) {
            log.error("[update]-failed update topic", e);
            throw new EntityFailedPersistException(PersistTypeEnum.DELETE, EntityTypeEnum.TRANSLATION);
        }
    }

    @Override
    public void delete(String id) {
    }
}
