package com.alfarizi.techblog.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;

@Table(name = "topic")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    @NotNull
    private String name;

    @Column
    private String superTopicId;

    @Column
    private String description;
    
    @Column
    private Timestamp createdAt;

    @Transient
    private List<Topic> subTopics;

    @Transient
    private Content content;

    @OneToMany(mappedBy = "topic")
    private List<Tag> tags;

    @OneToMany(mappedBy = "topic")
    private List<Reference> references;
}
