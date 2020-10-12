package home.train.bootstrap;

import home.train.model.Owner;
import home.train.model.Vet;
import home.train.service.OwnerService;
import home.train.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner= new Owner();
        owner.setFirstName("john");
        owner.setLastName("Doe");

        ownerService.save(owner);

        owner= new Owner();
        owner.setLastName("Public");
        owner.setFirstName("Mary");

        ownerService.save(owner);

        System.out.println("Loaded Owner .....");

        Vet vet= new Vet();
        vet.setFirstName("mario");
        vet.setLastName("vega");

        vetService.save(vet);

        vet=new Vet();
        vet.setFirstName("Nick");
        vet.setLastName("Tommy");

        vetService.save(vet);

        System.out.println("Loaded Vet ......");
    }
}
