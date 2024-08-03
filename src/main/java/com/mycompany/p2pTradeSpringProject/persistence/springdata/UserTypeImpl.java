package com.mycompany.p2pTradeSpringProject.persistence.springdata;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUserType;
import com.mycompany.p2pTradeSpringProject.persistence.entities.UserType;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserTypeImpl implements IDAOUserType {

    private final UserTypeRepository userTypeRepository;

    @Override
    public Optional<UserType> findById(int id) {
        return userTypeRepository.findById(id);
    }

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public List<UserType> findAll(int maxResults, int firstResult) {
        return userTypeRepository.findAll();
    }

    @Override
    public Integer create(UserType userType) {
        return userTypeRepository.save(userType).getId();
    }

    @Override
    public void update(UserType userType) {
        userTypeRepository.save(userType);
    }

    @Override
    public void delete(UserType userType) {
        userTypeRepository.delete(userType);
    }

    @Override
    public Long count() {
        return userTypeRepository.count();
    }
}
