package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.PresentationRepositoryInterfaces;
import com.nat.nat.entity.Presentation;

@Repository
public class PresentationRepository extends CommonRepository<Presentation> implements PresentationRepositoryInterfaces {

    public PresentationRepository() {
        super(Presentation.class);
    }
}
