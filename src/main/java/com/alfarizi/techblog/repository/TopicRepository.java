package com.alfarizi.techblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfarizi.techblog.entity.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findBySuperTopicId (String superTopicId);
}
