package com.squad11.locadora.services;

import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StorageService {

    RecoveredFile getImage(String fileName);

    void saveFile(MultipartFile file, String fileName);

    void deleteFile(String fileName);

    static String generateFileName(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    @Builder
    @Getter
    class RecoveredFile {
        private String url;

        private Resource resource;
    }
}
