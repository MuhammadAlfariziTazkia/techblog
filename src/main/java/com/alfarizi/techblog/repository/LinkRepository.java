package com.alfarizi.techblog.repository;

import com.alfarizi.techblog.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, String> {
}
