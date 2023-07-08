package com.nat.nat.api.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nat.nat.entity.CommonEntity;

public abstract class CommonRepository<T> {

    @Autowired
    protected EntityManager entityManager;
    
    private Class<T> entityClass;

    public CommonRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public T create(T entity) {
        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        if(entity instanceof CommonEntity) {
            CommonEntity commonEntity = (CommonEntity) entity;
            commonEntity.setCreatedAt(createdAt);
        }
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Transactional
    public void createBatch(List<T> entities) {
        for(T entity : entities) {
            LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
            if(entity instanceof CommonEntity) {
                CommonEntity commonEntity = (CommonEntity) entity;
                commonEntity.setCreatedAt(createdAt);
            }
            entityManager.persist(entity);
        }
    }

    public T getById(Long id) {
        return entityManager.find(entityClass, id);
    }
    
    @Transactional
    public void update(T entity) {
        LocalDateTime updatedAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        if(entity instanceof CommonEntity) {
            CommonEntity commonEntity = (CommonEntity) entity;
            commonEntity.setUpdatedAt(updatedAt);
        }
        entityManager.merge(entity);
    }

    @Transactional
    public void softDelete(Long id) {
        T entity = getById(id);
        LocalDateTime deletedAt = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        if(entity instanceof CommonEntity) {
            CommonEntity commonEntity = (CommonEntity) entity;
            commonEntity.setDeletedAt(deletedAt);
        }
        update(entity);
    }

    @Transactional
    public void hardDelete(Long id) {
        T entity = getById(id);
        entityManager.remove(entity);
    }

}
