package com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class VerificationRequest {
    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Surname is required")
    String surname;

    @NotBlank(message = "Passport number is required")
    String passportNumber;

    @NotNull(message = "Passport photo is required") //TODO: Does not work
    MultipartFile passportPhoto;

    @AssertTrue(message = "Passport photo should be in jpg or png format")
    boolean isPhotoFormatValid() {
        String fileName = passportPhoto.getOriginalFilename();
        if (fileName == null) {
            return false;
        }

        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
    }
}
