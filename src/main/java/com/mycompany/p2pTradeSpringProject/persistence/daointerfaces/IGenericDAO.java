package com.mycompany.p2pTradeSpringProject.persistence.daointerfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T> {

    Optional<T> findById(int id);

    List<T> findAll();

    List<T> findAll(int maxResults, int firstResult);

    void create(T t);

    void update(T t);

    void delete(T t);

    Long count();
}
