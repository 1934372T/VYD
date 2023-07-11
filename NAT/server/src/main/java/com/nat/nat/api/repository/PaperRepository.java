package com.nat.nat.api.repository;

import org.springframework.stereotype.Repository;

import com.nat.nat.api.repository.interfaces.PaperRepositoryInterface;
import com.nat.nat.entity.Paper;

@Repository
public class PaperRepository extends CommonRepository<Paper> implements PaperRepositoryInterface {

    public PaperRepository() {
        super(Paper.class);
    }
    
}
