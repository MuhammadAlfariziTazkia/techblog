package com.alfarizi.techblog.exception.custom;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException (EntityTypeEnum entityType) {
        super(entityType + " not found");
    }
}
