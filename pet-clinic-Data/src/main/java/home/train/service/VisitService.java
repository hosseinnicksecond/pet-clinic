package home.train.service;

import home.train.model.Visit;

import java.util.Set;

public interface VisitService extends CrudService<Visit,Long> {

    Set<Visit> findVisitByPetId(Long petId);
}
