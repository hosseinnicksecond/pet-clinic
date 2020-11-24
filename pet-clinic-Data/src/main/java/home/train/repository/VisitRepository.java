package home.train.repository;

import home.train.model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface VisitRepository extends CrudRepository<Visit ,Long> {

        Set<Visit> findByPetId(Long petId);
}
