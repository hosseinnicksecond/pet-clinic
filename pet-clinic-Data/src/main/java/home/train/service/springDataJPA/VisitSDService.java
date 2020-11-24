package home.train.service.springDataJPA;

import home.train.model.Visit;
import home.train.repository.VisitRepository;
import home.train.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Profile("springDataJpa")
public class VisitSDService implements VisitService {
    private final VisitRepository repository;

    public VisitSDService(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits= new HashSet<>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public Visit save(Visit visit) {
        Visit saved=repository.save(visit);
        return saved;
    }

    @Override
    public void delete(Visit visit) {
       repository.delete(visit);
    }

    @Override
    public void deleteById(Long aLong) {
       repository.deleteById(aLong);
    }

    @Override
    public Set<Visit> findVisitByPetId(Long petId) {
        return repository.findByPetId(petId);
    }
}
