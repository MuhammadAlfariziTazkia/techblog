package com.alfarizi.techblog.exception.custom;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final EntityTypeEnum entityType;

    public EntityNotFoundException (EntityTypeEnum entityType) {
        super(entityType + " not found");
        this.entityType = entityType;
    }
}
