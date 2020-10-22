package home.train.service.map;

import home.train.model.Speciality;
import home.train.model.Vet;
import home.train.service.SpecialitiesService;
import home.train.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default","map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialitiesService specialitiesService;

    public VetMapService(SpecialitiesService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet!=null){
            if(vet.getSpecialitySet().size()>0){
                vet.getSpecialitySet().forEach(s ->{
                    if(s.getId()==null){
                        Speciality saved=specialitiesService.save(s);
                        s.setId(saved.getId());
                    }
                });
            }
            return super.save(vet);
        }
        return null;
    }

    @Override
    public void delete(Vet t) {
       super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
