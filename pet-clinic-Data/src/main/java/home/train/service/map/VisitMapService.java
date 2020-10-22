package home.train.service.map;

import home.train.model.Visit;
import home.train.service.VisitService;

import java.util.Set;

public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService  {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit t) {
        if(t.getPet()==null || t.getPet().getOwner()==null || t.getPet().getId()==null
             || t.getPet().getOwner().getId()==null)
            throw new RuntimeException("Visit is Invalid");
        return super.save(t);
    }

    @Override
    public void delete(Visit t) {
        super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }
}
