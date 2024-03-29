package com.tech.kj.service;
import com.tech.kj.entity.VideoMetaEntity;
import com.tech.kj.exception.ApplicationConstant;
import com.tech.kj.exception.StorageException;
import com.tech.kj.repository.VideoRepository;
import com.tech.kj.service.external.LoadUserByUserNameExternalService;
import com.tech.kj.service.external.dto.RegisterUserDtoResponse;
import com.tech.kj.storage.service.MinioStorageService;
import com.tech.kj.util.Range;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVideoService implements VideoService {

    private final MinioStorageService storageService;
    private final VideoRepository fileMetadataRepository;
    private final LoadUserByUserNameExternalService loadUserByUserNameExternalService;

    @Override
    @Transactional
    public UUID save(MultipartFile video,String userName,String token) {
        try {
            UUID fileUuid = UUID.randomUUID();
            VideoMetaEntity metadata = VideoMetaEntity.builder()
                    .size(video.getSize())
                    .httpContentType(video.getContentType())
                    .build();
            //metadata.setId(fileUuid.toString());
            RegisterUserDtoResponse registerUserDto= loadUserByUserNameExternalService.fetchUserByUserName(userName,token);
            metadata.setUserId(registerUserDto.getId());
            VideoMetaEntity savedMetadata =  fileMetadataRepository.save(metadata);
            storageService.save(video.getInputStream(),video.getSize(), UUID.fromString(savedMetadata.getId()));
            return fileUuid;
        } catch (Exception ex) {
            log.error("Exception occurred when trying to save the file", ex);
            throw new StorageException(ApplicationConstant.ERR_STORAGE_MIN_IO,ApplicationConstant.ERR_STORAGE_MIN_IO_MSG);
        }
    }

    @Override
    public ChunkWithMetadata fetchChunk(UUID uuid, Range range) {
        VideoMetaEntity fileMetadata = fileMetadataRepository.findById(uuid.toString()).orElseThrow();
        return new ChunkWithMetadata(fileMetadata, readChunk(uuid, range, fileMetadata.getSize()));
    }


    private byte[] readChunk(UUID uuid, Range range, long fileSize) {
        long startPosition = range.getRangeStart();
        long endPosition = range.getRangeEnd(fileSize);
        int chunkSize = (int) (endPosition - startPosition + 1);
        try(InputStream inputStream = storageService.getInputStream(uuid, startPosition, chunkSize)) {
            return inputStream.readAllBytes();
        } catch (Exception exception) {
            log.error("Exception occurred when trying to read file with ID = {}", uuid);
            throw new StorageException(ApplicationConstant.ERR_STORAGE_MIN_IO,ApplicationConstant.ERR_STORAGE_MIN_IO_MSG);
        }
    }

    public record ChunkWithMetadata(VideoMetaEntity metadata, byte[] chunk) {}
}
