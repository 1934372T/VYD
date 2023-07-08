package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.PaperRepositoryInterfaces;
import com.nat.nat.entity.Paper;

@Repository
public class PaperRepository extends CommonRepository<Paper> implements PaperRepositoryInterfaces {
    
    public PaperRepository() {
        super(Paper.class);
    }
}
