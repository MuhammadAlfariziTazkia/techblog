package com.alfarizi.techblog.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @Transient
    private List<Reference> references;
}
