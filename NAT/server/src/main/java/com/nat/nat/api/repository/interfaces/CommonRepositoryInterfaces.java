package com.nat.nat.api.repository.interfaces;

import java.util.List;

public interface CommonRepositoryInterfaces<T> {
    void create(T entity);
    T getById(Long id);
    void createBatch(List<T> entities);
    void update(T entity);
}
