package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.PresentationRepositoryInterface;
import com.nat.nat.entity.Presentation;

@Repository
public class PresentationRepository extends CommonRepository<Presentation> implements PresentationRepositoryInterface {

    public PresentationRepository() {
        super(Presentation.class);
    }
    
}
