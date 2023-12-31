package com.tech.kj.storage.service;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioStorageService {

    private final MinioClient minioClient;

    @Value("${minio.put-object-part-size}")
    private Long putObjectPartSize;

    @Value("${minio.bucket.name}")
    private String COMMON_BUCKET_NAME;

    public void save(InputStream inputStream,long fileSize, UUID uuid) throws Exception {
        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(COMMON_BUCKET_NAME)
                        .object(uuid.toString())
                        .stream(inputStream, fileSize, putObjectPartSize)
                        .build()
        );
    }

    public InputStream getInputStream(UUID uuid, long offset, long length) throws Exception {
        return minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(COMMON_BUCKET_NAME)
                        .offset(offset)
                        .length(length)
                        .object(uuid.toString())
                        .build());
    }
}
