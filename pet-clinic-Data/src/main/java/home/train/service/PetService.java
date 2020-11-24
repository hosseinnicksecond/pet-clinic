package home.train.service;

import home.train.model.Owner;
import home.train.model.Pet;


public interface PetService extends CrudService<Pet ,Long> {

    Pet updatePet(Owner owner,Pet pet);

}
