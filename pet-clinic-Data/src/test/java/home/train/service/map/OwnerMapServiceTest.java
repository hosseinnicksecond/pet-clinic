package home.train.service.map;

import home.train.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerMapServiceTest {

    OwnerMapService service;


    @BeforeEach
    void setUp() {
        service = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        service.save(Owner.builder().id(1L).firstName("John").lastName("Doe").build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = service.findAll();
        assertThat(owners.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        Owner owner = service.findById(1L);
        assertThat(owner.getFirstName()).isEqualToIgnoringCase("John");
    }

    @Test
    void saveByExistingId() {
        Owner owner2 = Owner.builder().id(2L).firstName("mary").build();
        Owner savedOwner = service.save(owner2);
        assertThat(savedOwner.getId()).isEqualTo(2L);
    }

    @Test
    void saveNoId() {
        Owner owner2 = Owner.builder().firstName("mario").build();
        Owner savedOwner = service.save(owner2);
        assertThat(savedOwner.getId()).isEqualTo(2L);
    }

    @Test
    void delete() {
        service.delete(service.findById(1L));
        assertThat(service.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        assertThat(service.findAll().size()).isEqualTo(0);
    }

    @Test
    void findByLastName() {
        Owner owner = service.findByLastName("Doe");
        assertThat(owner.getFirstName()).isEqualToIgnoringCase("John");
    }
}