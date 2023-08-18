package com.alfarizi.techblog.entity;

import com.alfarizi.techblog.constant.enumeration.LinkTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    private String url;

    @Column
    @Enumerated(EnumType.STRING)
    private LinkTypeEnum type;
}
