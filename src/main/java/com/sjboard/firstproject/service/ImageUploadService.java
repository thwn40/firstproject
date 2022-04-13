package com.sjboard.firstproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageUploadService {

    @Value("${file.dir}")
    private String fileDir;

    public String StoreImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
            return fullPath;
        }
        throw new IOException("asdf");
    }
}

