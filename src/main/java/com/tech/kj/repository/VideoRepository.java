package com.tech.kj.repository;

import com.tech.kj.entity.VideoMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoMetaEntity,String> {
}
