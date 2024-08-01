package com.mycompany.p2pTradeSpringProject.service;

import com.mycompany.p2pTradeSpringProject.dto.VerificationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserVerification;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.persistence.entities.UserVerification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserVerificationService {

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

        UserVerification userVerification = UserVerification.builder()
                .name(verificationRequest.getName())
                .surname(verificationRequest.getSurname())
                .passportNumber(verificationRequest.getPassportNumber())
                .passportPhotoReference(verificationRequest.getPassportPhotoReference())
                .build();

        daoUserVerification.create(userVerification);
        user.setUserVerification(userVerification);
        daoUser.update(user);


    }
}
