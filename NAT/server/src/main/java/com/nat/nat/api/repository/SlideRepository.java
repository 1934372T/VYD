package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.SlideRepositoryInterfaces;
import com.nat.nat.entity.Slide;

@Repository
public class SlideRepository extends CommonRepository<Slide> implements SlideRepositoryInterfaces {

    public SlideRepository() {
        super(Slide.class);
    }
}
