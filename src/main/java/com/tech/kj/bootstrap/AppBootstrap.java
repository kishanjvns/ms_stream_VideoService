package com.tech.kj.bootstrap;

import com.tech.kj.entity.VideoMetaEntity;
import com.tech.kj.repository.VideoRepository;
import com.tech.kj.storage.service.MinioStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
@Slf4j
public class AppBootstrap implements CommandLineRunner {
    private final MinioStorageService storageService;
    private final VideoRepository fileMetadataRepository;
    public AppBootstrap(MinioStorageService storageService, VideoRepository fileMetadataRepository){
        this.storageService = storageService;
        this.fileMetadataRepository = fileMetadataRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        //createVideoMetaEntityInstance();
    }
    /*public void createVideoMetaEntityInstance() throws IOException {

        for(int i=1; i<= 3;i++ ){
            InputStream ioStream = AppBootstrap.class
                    .getClassLoader()
                    .getResourceAsStream("static/"+i+".jpg");
            UUID fileUuid = UUID.randomUUID();
            VideoMetaEntity metadata = VideoMetaEntity.builder()
                    .id(fileUuid.toString())
                    .size(Long.parseLong(ioStream.available()+""))
                    .httpContentType(".jpg")
                    .build();
            log.info("persisting  image metadata");
            fileMetadataRepository.save(metadata);
            try {
                storageService.save(ioStream,metadata.getSize(), fileUuid);
            } catch (Exception e) {
                log.info("Error occurred while storing image {}.jpg to MINOIO",i);
                throw new RuntimeException(e);
            }
        }
    }*/
}
