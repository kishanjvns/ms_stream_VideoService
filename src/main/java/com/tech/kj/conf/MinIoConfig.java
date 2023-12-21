package com.tech.kj.conf;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIoConfig {

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.secretKey}")
    private String minioSecretKey;
    @Value("${minio.accessKey}")
    private String minioAccessKey;

    @Value("${minio.bucket.name}")
    private String COMMON_BUCKET_NAME;

    @Bean
    public MinioClient getMinioClient(){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioAccessKey,minioSecretKey)
                .build();
        return minioClient;
    }
}
