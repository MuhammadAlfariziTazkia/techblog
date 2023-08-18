package com.alfarizi.techblog.service.intr;

import com.alfarizi.techblog.dto.request.ReferenceDto;
import com.alfarizi.techblog.entity.Reference;

import java.util.List;

public interface ReferenceService extends CoreService<Reference, ReferenceDto> {

    List<Reference> getByTopicId(String topicId);
}
