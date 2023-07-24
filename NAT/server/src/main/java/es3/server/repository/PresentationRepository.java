package es3.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es3.server.entity.Presentation;
import java.util.List;


@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long>{
    @Query("SELECT DISTINCT term FROM Presentation")
    List<String> getAllTerms();
}
