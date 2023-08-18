package com.alfarizi.techblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reference {
    
    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private String topicId;

    @OneToOne
    @JoinColumn(name = "link_id")
    private Link link;
}
