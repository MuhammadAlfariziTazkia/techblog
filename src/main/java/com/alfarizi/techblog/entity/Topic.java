package com.alfarizi.techblog.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;

@Table(name = "topic")
@Entity
@Data
@Builder
public class Topic {
    
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private UUID superTopicId;

    @Column
    private String description;
    
    @Column
    private Timestamp createdAt;

    @OneToMany(mappedBy = "topic")
    private List<Tag> tags;

    @OneToMany(mappedBy = "topic")
    private List<Reference> references;
}
