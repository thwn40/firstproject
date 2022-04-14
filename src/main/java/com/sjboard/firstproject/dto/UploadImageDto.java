package com.sjboard.firstproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadImageDto {
    private MultipartFile img;
}
