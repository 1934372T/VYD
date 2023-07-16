package es3.server.repository;

import es3.server.entity.Slide;

public interface SlideRepository extends CommonRepository<Slide> {}

class slideRepository extends commonRepository<Slide> implements SlideRepository {
    public slideRepository() {
        super(Slide.class);
    }
}
