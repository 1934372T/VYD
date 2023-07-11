package com.nat.nat.api.repository.interfaces;

import com.nat.nat.entity.Presentation;

public interface PresentationRepositoryInterface extends CommonRepositoryInterface<Presentation> {
    void softDelete(Long id);
    void hardDelete(Long id);
}
