package home.train.bootstrap;

import home.train.model.*;
import home.train.service.OwnerService;
import home.train.service.PetTypeService;
import home.train.service.SpecialitiesService;
import home.train.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;
   private final PetTypeService petTypeService;
   private final SpecialitiesService specialitiesService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }

    @Override
    public void run(String... args) throws Exception {

        LocalData();
    }

    private void LocalData() {
        PetType Dog= new PetType();
        Dog.setName("Dog");
        PetType SaveDogPetType=petTypeService.save(Dog);

        PetType Cat= new PetType();
        Cat.setName("Cat");
        PetType SaveCatPetType=petTypeService.save(Cat);


        Owner owner= new Owner();
        owner.setFirstName("john");
        owner.setLastName("Doe");
        owner.setAddress("22 1.Street");
        owner.setCity("London");
        owner.setPhoneNumber("1234565");

        Pet DogPets=new Pet();
        DogPets.setPetType(SaveDogPetType);
        DogPets.setName("oscar");
        DogPets.setBirthDay(LocalDate.now());
        DogPets.setOwner(owner);
        owner.getPets().add(DogPets);

        ownerService.save(owner);

        owner= new Owner();
        owner.setLastName("Public");
        owner.setFirstName("Mary");
        owner.setAddress("22 1.Street");
        owner.setCity("London");
        owner.setPhoneNumber("1234565");

        Pet CatPet= new Pet();
        CatPet.setBirthDay(LocalDate.now());
        CatPet.setName("kitty");
        CatPet.setPetType(SaveCatPetType);
        CatPet.setOwner(owner);
        owner.getPets().add(CatPet);

        ownerService.save(owner);

        System.out.println("Loaded Owner .....");

        Speciality surgery= new Speciality();
        surgery.setDescription("Surgery");
        specialitiesService.save(surgery);

        Speciality radiology= new Speciality();
        radiology.setDescription("RadioLogy");
        specialitiesService.save(radiology);

        Speciality dentistry= new Speciality();
        dentistry.setDescription("Dentistry");
        specialitiesService.save(dentistry);

        Vet vet= new Vet();
        vet.setFirstName("mario");
        vet.setLastName("vega");
        vet.getSpecialitySet().add(surgery);
        vet.getSpecialitySet().add(radiology);
        vetService.save(vet);

        vet=new Vet();
        vet.setFirstName("Nick");
        vet.setLastName("Tommy");
        vet.getSpecialitySet().add(dentistry);
        vet.getSpecialitySet().add(radiology);
        vetService.save(vet);

        System.out.println("Loaded Vet ......");
    }
}
