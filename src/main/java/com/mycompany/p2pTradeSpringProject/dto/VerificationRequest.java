package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class VerificationRequest {
    private String name;
    private String surname;
    private String passportNumber;
    private MultipartFile passportPhoto;
    private String passportPhotoReference;
}
