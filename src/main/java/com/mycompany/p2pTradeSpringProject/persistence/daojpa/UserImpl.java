package com.mycompany.p2pTradeSpringProject.persistence.daojpa;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserImpl implements IDAOUser {

    private final UserRepository userRepository;


    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(int maxResults, int firstResult) {
        return userRepository.findAll();
    }

    @Override
    public Integer create(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
