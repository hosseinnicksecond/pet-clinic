package home.train.service.springDataJPA;

import home.train.model.PetType;
import home.train.repository.PetTypeRepository;
import home.train.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class PetTypeSDService implements PetTypeService {

    private final PetTypeRepository repository;

    public PetTypeSDService(PetTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes= new HashSet<>();
        repository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return repository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        repository.delete(petType);
    }

    @Override
    public void deleteById(Long aLong) {
       repository.deleteById(aLong);
    }
}
