package com.nat.nat.api.repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nat.nat.api.repository.interfaces.PresentationRepositoryInterfaces;
import com.nat.nat.entity.Presentation;

@Repository
public class PresentationRepository extends CommonRepository<Presentation> implements PresentationRepositoryInterfaces {

    public PresentationRepository() {
        super(Presentation.class);
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        Presentation presentation = getById(id);
        presentation.setDeletedAt(LocalDateTime.now());
        update(presentation);
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        Presentation presentation = getById(id);
        entityManager.remove(presentation);
    }
}
