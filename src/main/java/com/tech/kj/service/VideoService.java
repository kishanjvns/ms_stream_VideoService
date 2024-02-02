package com.tech.kj.service;

import com.tech.kj.util.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {
    public UUID save(MultipartFile file,String userName,String authorizationHeader);

    DefaultVideoService.ChunkWithMetadata fetchChunk(UUID uuid, Range range);
}
