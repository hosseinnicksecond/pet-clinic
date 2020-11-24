package home.train.service.springDataJPA;

import home.train.model.Owner;
import home.train.model.Pet;
import home.train.repository.PetRepository;
import home.train.service.OwnerService;
import home.train.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Profile("springDataJpa")
public class PetSDService implements PetService {

    private final PetRepository petRepository;
    private final OwnerService ownerService;

    public PetSDService(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
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
    @Transactional
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

    @Override
    public Pet updatePet(Owner owner, Pet pet) {
        Optional<Pet> comPet=owner.getPets().stream()
                .filter(pet1 -> pet1.getId().equals(pet.getId()))
                .findFirst();
        if(comPet.isPresent()){
            Pet pet1=comPet.get();
            pet1.setPetType(pet.getPetType());
            pet1.setName(pet.getName());
            pet1.setBirthDay(pet.getBirthDay());
            pet1.setVisits(pet.getVisits());
        }
        Owner savedOwner=ownerService.save(owner);

        Optional<Pet> savedPet=savedOwner.getPets().stream()
                .filter(pet1 -> pet1.getId().equals(pet.getId()))
                .filter(pet1 -> pet1.getName().equalsIgnoreCase(pet.getName()))
                .filter(pet1 -> pet1.getBirthDay().isEqual(pet.getBirthDay()))
                .findFirst();

        if(savedPet.isPresent()){
            log.info("owner id of updated pet id {}",savedPet.get().getOwner().getId());
            return savedPet.get();

        }
        throw new RuntimeException("cannot update "+ pet.getName() +" correctly");
    }
}
