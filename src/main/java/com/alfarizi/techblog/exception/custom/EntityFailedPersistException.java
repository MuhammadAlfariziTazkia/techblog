package com.alfarizi.techblog.exception.custom;

import com.alfarizi.techblog.constant.enumeration.EntityTypeEnum;
import com.alfarizi.techblog.constant.enumeration.PersistTypeEnum;

public class EntityFailedPersistException extends RuntimeException {

    public EntityFailedPersistException (PersistTypeEnum persistType, EntityTypeEnum entityType) {
        super("FAILED " + persistType + " " + entityType);
    }
}
