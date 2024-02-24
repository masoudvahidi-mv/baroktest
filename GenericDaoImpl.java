package com.example.BarokTest.genericDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public abstract class GenericDaoImpl<T,ID extends Serializable> implements GenericDao<T,ID> {
    private final JpaRepository<T, ID> repository;

    public GenericDaoImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T add(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return (Optional<T>) repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
