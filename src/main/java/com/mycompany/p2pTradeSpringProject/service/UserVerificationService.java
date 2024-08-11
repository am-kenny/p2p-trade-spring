package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.request.VerificationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserVerification;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.domain.entity.UserVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserVerificationService {

    //TODO: Change this to a more appropriate location
    private static final String STATIC_PATH = "D:\\IdeaProjects\\p2pTradeSpringProject\\src\\main\\resources\\static\\";
    private static final String PASSPORT_PHOTO_DIRECTORY = "passport_images\\";

    private final IDAOUserVerification daoUserVerification;
    private final IDAOUser daoUser;

    @Transactional(readOnly = true)
    public boolean isVerified(int id) {
        Optional<User> user = daoUser.findById(id);
        return user.filter(value -> value.getUserVerification() != null).isPresent();
    }

    public void verifyUser(int id, VerificationRequest verificationRequest) {
        Optional<User> userOptional = daoUser.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        User user = userOptional.get();

        if (user.getUserVerification() != null) {
            throw new IllegalArgumentException("User with id " + id + " is already verified");
        }

        MultipartFile passportPhoto = verificationRequest.getPassportPhoto();

        String originalFilename = passportPhoto.getOriginalFilename();

        String passportPhotoReference =  PASSPORT_PHOTO_DIRECTORY + originalFilename;
        String filePath = STATIC_PATH + passportPhotoReference;


        try {
            //save photo file locally
            passportPhoto.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to save passport photo");
        }

        UserVerification userVerification = UserVerification.builder()
                .name(verificationRequest.getName())
                .surname(verificationRequest.getSurname())
                .passportNumber(verificationRequest.getPassportNumber())
                .passportPhotoReference(passportPhotoReference)
                .build();

        daoUserVerification.create(userVerification);
        user.setUserVerification(userVerification);
        daoUser.update(user);

    }
}
