package home.train.service.map;

import home.train.model.Vet;
import home.train.service.CrudService;

import java.util.Set;

public class VetMapService extends AbstractMapService<Vet, Long> implements CrudService<Vet,Long> {

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
        return super.save(vet.getId(),vet);
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