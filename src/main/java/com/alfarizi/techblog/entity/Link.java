package com.alfarizi.techblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alfarizi.techblog.constant.enumeration.LinkTypeEnum;

@Entity
@Table
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

    @OneToOne(mappedBy = "link")
    private Reference reference;
}
