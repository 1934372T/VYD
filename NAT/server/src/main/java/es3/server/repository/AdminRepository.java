package es3.server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es3.server.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>{
    List<Admin> getByEmail(String email);
}
