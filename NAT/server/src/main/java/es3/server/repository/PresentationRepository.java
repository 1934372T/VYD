package es3.server.repository;

import es3.server.entity.Presentation;

public interface PresentationRepository extends CommonRepository<Presentation> {}

class presentationRepository extends commonRepository<Presentation> implements PresentationRepository {
    public presentationRepository() {
        super(Presentation.class);
    }
}
