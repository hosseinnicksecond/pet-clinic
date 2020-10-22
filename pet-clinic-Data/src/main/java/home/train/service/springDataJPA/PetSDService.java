package home.train.service.springDataJPA;

import home.train.model.Pet;
import home.train.repository.PetRepository;
import home.train.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class PetSDService implements PetService {

    private final PetRepository petRepository;

    public PetSDService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets= new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
      petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long aLong) {
       petRepository.deleteById(aLong);
    }
}
