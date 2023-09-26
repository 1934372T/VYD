package es3.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es3.server.entity.Slide;

@Repository
public interface SlideRepository extends CrudRepository<Slide, Long>{
    
}
