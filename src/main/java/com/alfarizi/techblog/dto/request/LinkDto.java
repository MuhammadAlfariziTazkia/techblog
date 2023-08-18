package com.alfarizi.techblog.dto.request;

import com.alfarizi.techblog.constant.enumeration.LinkTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {

    String url;

    LinkTypeEnum type;
}
