package home.train.service.springDataJPA;

import home.train.model.Speciality;
import home.train.repository.SpecialityRepository;
import home.train.service.SpecialitiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class VetSpecialitySDService implements SpecialitiesService {

    private final SpecialityRepository repository;

    public VetSpecialitySDService(SpecialityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities= new HashSet<>();
        repository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return repository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        repository.delete(speciality);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}

