package home.train.bootstrap;

import home.train.model.*;
import home.train.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;
   private final PetTypeService petTypeService;
   private final SpecialitiesService specialitiesService;
   private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialitiesService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args)  {

       int count=ownerService.findAll().size();
       if (count==0){
           LocalData();
       }
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
        owner.setTelephone("1234565");

        Pet DogPet=new Pet();
        DogPet.setPetType(SaveDogPetType);
        DogPet.setName("oscar");
        DogPet.setBirthDay(LocalDate.now());
        DogPet.setOwner(owner);
        owner.getPets().add(DogPet);

        Visit visit= new Visit();
        visit.setDate(LocalDate.now());
        visit.setPet(DogPet);
        visit.setDescription("for running backward");

        Owner savedOwner=ownerService.save(owner);

        Visit savedVisit=visitService.save(visit);

        System.out.println("Loaded Owner"+ savedOwner.getId()+" and visit "+savedVisit.getId()
                + " pet name "+savedVisit.getPet().getName());

        Owner owner2= new Owner();
        owner2.setLastName("public");
        owner2.setFirstName("Mary");
        owner2.setAddress("22 1.Street");
        owner2.setCity("London");
        owner2.setTelephone("1234565");

        Pet CatPet= new Pet();
        CatPet.setBirthDay(LocalDate.now());
        CatPet.setName("kitty");
        CatPet.setPetType(SaveCatPetType);
        CatPet.setOwner(owner2);
        owner2.getPets().add(CatPet);

        Visit visit2= new Visit();
        visit2.setDate(LocalDate.now());
        visit2.setPet(CatPet);
        visit2.setDescription("for running backward");

        System.out.println("*********"+visit.getPet().getName());

        Owner savedOwner2 =ownerService.save(owner2);
        Visit savedVisit2=visitService.save(visit2);

        if(savedVisit2 !=null) {
//            log.info("savedVisit2 pet name {}",savedVisit2.getPet().getName());
            System.out.println("Loaded Owner" + savedOwner2.getId() + " and visit " + savedVisit2.getId()
                    + " pet name " + savedVisit2.getPet().getName());
        }else {
            System.out.println("visit2 not saved");
        }

        Owner owner3= new Owner();
        owner3.setLastName("public");
        owner3.setFirstName("Tim");
        owner3.setAddress("36 1.vally");
        owner3.setCity("moscow");
        owner3.setTelephone("1987456");

        Pet DogPet2= new Pet();
        DogPet2.setBirthDay(LocalDate.now());
        DogPet2.setName("rex");
        DogPet2.setPetType(SaveDogPetType);
        DogPet2.setOwner(owner3);
        owner3.getPets().add(DogPet2);

        Visit visit3= new Visit();
        visit3.setDate(LocalDate.now());
        visit3.setPet(DogPet2);
        visit3.setDescription("for too much sleep");


        Owner savedOwner3=ownerService.save(owner3);

        Visit savedVisit3=visitService.save(visit3);

        System.out.println("Loaded Owner"+ savedOwner3.getId()+" and visit "+savedVisit3.getId()
                + " pet name "+savedVisit3.getPet().getName());

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
