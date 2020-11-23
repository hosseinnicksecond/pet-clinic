package home.train.service;

import home.train.model.Owner;

import java.util.Set;


public interface OwnerService extends CrudService<Owner ,Long> {

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);

}
