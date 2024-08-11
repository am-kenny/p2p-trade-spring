package com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.request;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class VerificationRequest { //TODO: Add validation
    String name;
    String surname;
    String passportNumber;
    MultipartFile passportPhoto;
}
