package home.train.service;

import home.train.model.Owner;


public interface OwnerService extends CrudService<Owner ,Long> {

    Owner findByLastName(String lastName);

}
