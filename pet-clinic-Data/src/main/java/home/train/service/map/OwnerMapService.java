package home.train.service.map;

import home.train.model.Owner;
import home.train.model.Pet;
import home.train.service.OwnerService;
import home.train.service.PetService;
import home.train.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner ,Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if(owner!=null) {
            if(owner.getPets()!=null){
                owner.getPets().forEach(pet -> {
                   if(pet.getPetType()!=null){
                       if(pet.getPetType().getId()==null)
                           pet.setPetType(petTypeService.save(pet.getPetType()));
                   }else throw new RuntimeException("pet type must be saved");

                   if(pet.getId()==null){
                       Pet savedPet=petService.save(pet);
                       pet.setId(savedPet.getId());
                   }
                });
            }
            return super.save(owner);
        }else
            return null;
    }

    @Override
    public void delete(Owner t) {
          super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
