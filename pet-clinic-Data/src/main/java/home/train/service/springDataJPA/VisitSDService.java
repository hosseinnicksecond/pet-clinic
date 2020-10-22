package home.train.service.springDataJPA;

import home.train.model.Visit;
import home.train.repository.VisitRepository;
import home.train.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
    public Visit save(Visit visit) {
        return repository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
       repository.delete(visit);
    }

    @Override
    public void deleteById(Long aLong) {
       repository.deleteById(aLong);
    }
}
