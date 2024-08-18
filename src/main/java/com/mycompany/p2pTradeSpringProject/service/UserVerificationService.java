package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.request.VerificationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.response.VerificationResponse;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserVerification;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.domain.entity.UserVerification;
import com.mycompany.p2pTradeSpringProject.component.ValidationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserVerificationService {

    //TODO: Change this to a more appropriate location
    private static final String STATIC_PATH = "D:\\IdeaProjects\\p2pTradeSpringProject\\src\\main\\resources\\static\\";
    private static final String PASSPORT_PHOTO_DIRECTORY = "passport_images\\";

    private final IDAOUserVerification daoUserVerification;
    private final IDAOUser daoUser;

    private final ValidationWrapper validationWrapper;


    @Transactional(readOnly = true)
    public boolean isVerified(Integer id) {
        Optional<User> user = daoUser.findById(id);
        return user.filter(value -> value.getUserVerification() != null).isPresent();
    }

    @Transactional
    public VerificationResponse verifyUser(Integer userId, VerificationRequest verificationRequest) {

        Optional<User> userOptional = daoUser.findById(userId);
        Set<ValidationError> errors = validateVerificationRequest(userOptional, verificationRequest);

        if (!errors.isEmpty()) {
            return VerificationResponse.builder()
                    .success(false)
                    .errors(errors)
                    .build();
        }

        User user = userOptional.orElseThrow(() ->
                new IllegalArgumentException("User with this id not found"));

        String passportPhotoReference = savePassportPhoto(verificationRequest.getPassportPhoto());

        UserVerification userVerification = UserVerification.builder()
                .name(verificationRequest.getName())
                .surname(verificationRequest.getSurname())
                .passportNumber(verificationRequest.getPassportNumber())
                .passportPhotoReference(passportPhotoReference)
                .build();

        Integer verificationId = daoUserVerification.create(userVerification);
        user.setUserVerification(userVerification);
        daoUser.update(user);

        return VerificationResponse.builder()
                .success(true)
                .verificationId(verificationId)
                .build();
    }

    private Set<ValidationError> validateVerificationRequest(Optional<User> userOptional, VerificationRequest request) {
        Set<ValidationError> errors = validationWrapper.validateObject(request);

        if (daoUserVerification.existsByPassportNumber(request.getPassportNumber())) {
            errors.add(ValidationError.builder()
                    .message("User with this passport number already exists")
                    .build());
        }

        if (userOptional.isEmpty()) {
            errors.add(ValidationError.builder()
                    .message("User with this id not found")
                    .build());
        }

        if (userOptional.filter(value -> value.getUserVerification() != null).isPresent()) {
            errors.add(ValidationError.builder()
                    .message("User with this id is already verified")
                    .build());
        }

        return errors;
    }

    private String savePassportPhoto(MultipartFile passportPhoto) {
        String originalFilename = passportPhoto.getOriginalFilename();

        String passportPhotoReference = PASSPORT_PHOTO_DIRECTORY + originalFilename;
        String filePath = STATIC_PATH + passportPhotoReference;


        try {
            //save photo file locally
            passportPhoto.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to save passport photo");
        }
        return passportPhotoReference;
    }
}
