package com.mycompany.p2pTradeSpringProject.persistence.daojpa;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserVerification;
import com.mycompany.p2pTradeSpringProject.domain.entity.UserVerification;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.UserVerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserVerificationImpl implements IDAOUserVerification {

    private final UserVerificationRepository userVerificationRepository;

    @Override
    public Optional<UserVerification> findById(int id) {
        return userVerificationRepository.findById(id);
    }

    @Override
    public List<UserVerification> findAll() {
        return userVerificationRepository.findAll();
    }

    @Override
    public List<UserVerification> findAll(int maxResults, int firstResult) {
        return userVerificationRepository.findAll();
    }

    @Override
    public Integer create(UserVerification userVerification) {
        return userVerificationRepository.save(userVerification).getId();
    }

    @Override
    public void update(UserVerification userVerification) {
        userVerificationRepository.save(userVerification);
    }

    @Override
    public void delete(UserVerification userVerification) {
        userVerificationRepository.delete(userVerification);
    }

    @Override
    public Long count() {
        return userVerificationRepository.count();
    }

    @Override
    public boolean existsByPassportNumber(String passportNumber) {
        return userVerificationRepository.existsByPassportNumber(passportNumber);
    }
}
