package home.train.service.map;

import home.train.model.Pet;
import home.train.service.PetService;

import java.util.Set;


public class PetMapService extends AbstractMapService<Pet , Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(),pet);
    }

    @Override
    public void delete(Pet t) {
        super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
         super.deleteById(id);
    }
}
