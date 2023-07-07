package com.alfarizi.techblog.repository;

import com.alfarizi.techblog.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, String> {
}
