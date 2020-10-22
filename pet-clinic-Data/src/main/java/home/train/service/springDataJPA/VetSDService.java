package home.train.service.springDataJPA;

import home.train.model.Vet;
import home.train.repository.VetRepository;
import home.train.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springDataJpa")
public class VetSDService implements VetService {

    private final VetRepository vetRepository;

    public VetSDService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets= new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
       vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long aLong) {
         vetRepository.deleteById(aLong);
    }
}
