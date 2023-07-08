package com.nat.nat.api.repository.interfaces;

import com.nat.nat.entity.Presentation;

public interface PresentationRepositoryInterfaces extends CommonRepositoryInterfaces<Presentation> {
    public void softDelete(Long id);
    public void hardDelete(Long id);
}
