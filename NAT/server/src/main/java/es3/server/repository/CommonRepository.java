package es3.server.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es3.server.entity.CommonEntity;

public interface CommonRepository<T> {
    T       create(T entity);
    void    createBatch(List<T> entities);
    T       getById(Long id);
    List<T> getWithQuery(List<String> queries);
    void    update(T entity);
    void    softDelete(Long id);
    void    hardDelete(Long id);
}


abstract class commonRepository<T> implements CommonRepository<T> {
    private static final String TIME_ZOME = "Asia/Tokyo";

    @Autowired
    protected EntityManager entityManager;
    
    private Class<T> entityClass;

    public commonRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public T create(T entity) {
        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of(TIME_ZOME));
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
            LocalDateTime createdAt = LocalDateTime.now(ZoneId.of(TIME_ZOME));
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

    public List<T> getWithQuery(List<String> queries) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        
        List<Predicate> predicates = new ArrayList<>();
        for(String condition : queries) {
            String[] conditionParts = condition.split("=");
            if(conditionParts.length != 2) {
                throw new IllegalArgumentException("Invalid condition: " + condition);
            }
            String fieldName = conditionParts[0].trim();
            String value = conditionParts[1].trim();
            predicates.add(cb.equal(root.get(fieldName), value));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        
        return entityManager.createQuery(cq).getResultList();
    }
    
    @Transactional
    public void update(T entity) {
        LocalDateTime updatedAt = LocalDateTime.now(ZoneId.of(TIME_ZOME));
        if(entity instanceof CommonEntity) {
            CommonEntity commonEntity = (CommonEntity) entity;
            commonEntity.setUpdatedAt(updatedAt);
        }
        entityManager.merge(entity);
    }

    @Transactional
    public void softDelete(Long id) {
        T entity = getById(id);
        LocalDateTime deletedAt = LocalDateTime.now(ZoneId.of(TIME_ZOME));
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
