package com.alfarizi.techblog.repository;

import com.alfarizi.techblog.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String> {

    List<Reference> findByTopicId(String topicId);
}
