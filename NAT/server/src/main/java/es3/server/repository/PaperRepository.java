package es3.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es3.server.entity.Paper;

@Repository
public interface PaperRepository extends CrudRepository<Paper, Long> {
    
}
