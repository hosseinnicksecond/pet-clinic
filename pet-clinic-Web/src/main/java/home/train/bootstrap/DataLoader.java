package home.train.bootstrap;

import home.train.model.Owner;
import home.train.model.Vet;
import home.train.service.OwnerService;
import home.train.service.VetService;
import home.train.service.map.OwnerMapService;
import home.train.service.map.VetMapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;

    public DataLoader() {
        this.ownerService= new OwnerMapService();
        this.vetService=new VetMapService();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner= new Owner();
        owner.setFirstName("john");
        owner.setId(1l);
        owner.setLastName("Doe");

        ownerService.save(owner);

        owner= new Owner();
        owner.setLastName("Public");
        owner.setFirstName("Mary");
        owner.setId(2l);

        ownerService.save(owner);

        System.out.println("Loaded Owner .....");

        Vet vet= new Vet();
        vet.setId(1l);
        vet.setFirstName("mario");
        vet.setLastName("vega");

        vetService.save(vet);

        vet=new Vet();
        vet.setId(2l);
        vet.setFirstName("Nick");
        vet.setLastName("Tommy");

        vetService.save(vet);

        System.out.println("Loaded Vet ......");
    }
}
