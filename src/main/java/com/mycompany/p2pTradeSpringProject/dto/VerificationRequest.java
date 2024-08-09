package com.mycompany.p2pTradeSpringProject.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class VerificationRequest {
    String name;
    String surname;
    String passportNumber;
    MultipartFile passportPhoto;
}
