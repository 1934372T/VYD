package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.SlideRepositoryInterface;
import com.nat.nat.entity.Slide;

@Repository
public class SlideRepository extends CommonRepository<Slide> implements SlideRepositoryInterface {

    public SlideRepository() {
        super(Slide.class);
    }
    
}
