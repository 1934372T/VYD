package com.nat.nat.api.repository.interfaces;

import java.util.List;

public interface CommonRepositoryInterfaces<T> {
    T create(T entity);
    void createBatch(List<T> entities);
    T getById(Long id);
    List<T> getWithQuery(List<String> queries);
    void update(T entity);
}
