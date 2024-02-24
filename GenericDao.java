package com.example.BarokTest.genericDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface GenericDao<T,ID> {
    T add(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T entity);
}
