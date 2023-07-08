package com.nat.nat.api.repository;

import com.nat.nat.api.repository.interfaces.SlideRepositoryInterfaces;
import com.nat.nat.entity.Slide;

public class SlideRepository extends CommonRepository<Slide> implements SlideRepositoryInterfaces {

    public SlideRepository() {
        super(Slide.class);
    }
}
