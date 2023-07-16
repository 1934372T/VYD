package es3.server.repository;

import es3.server.entity.Paper;

public interface PaperRepository extends CommonRepository<Paper> {}

class paperRepository extends commonRepository<Paper> implements PaperRepository {
    public paperRepository() {
        super(Paper.class);
    }
}
