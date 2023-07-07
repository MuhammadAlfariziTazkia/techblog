package com.alfarizi.techblog.exception.custom;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;
import lombok.Getter;

@Getter
public class EntityFailedPersistException extends RuntimeException {

    private final EntityTypeEnum entityType;

    public EntityFailedPersistException (PersistTypeEnum persistType, EntityTypeEnum entityType) {
        super("FAILED " + persistType + " " + entityType);
        this.entityType = entityType;
    }
}
