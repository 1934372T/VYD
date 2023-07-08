package com.nat.nat.api.repository;

import java.util.List;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class CommonRepository<T> {

    @Autowired
    protected EntityManager entityManager;
    
    private Class<T> entityClass;

    public CommonRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public T getById(Long id) {
        return entityManager.find(entityClass, id);
    }
    
    @Transactional
    public void createBatch(List<T> entities) {
        for(T entity : entities) {
            entityManager.persist(entity);
        }
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

}
