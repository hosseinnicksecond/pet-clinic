package home.train.service.springDataJPA;

import home.train.model.Owner;
import home.train.repository.OwnerRepository;
import home.train.repository.PetRepository;
import home.train.repository.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OwnerSDServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDService service;

    @Test
    void findByLastName() {
        Owner owner = Owner.builder().id(1L).lastName("Smith").build();
        given(ownerRepository.findByLastName(anyString())).willReturn(owner);

        Owner find = service.findByLastName("Smith");

        assertEquals("Smith", find.getLastName());
        then(ownerRepository).should().findByLastName(anyString());
    }

    @Test
    void findAll() {
        Set<Owner> ownerList = new HashSet<>();
        ownerList.add(Owner.builder().id(1L).lastName("Doe").build());
        ownerList.add(Owner.builder().id(2L).lastName("Smith").build());
        given(ownerRepository.findAll()).willReturn(ownerList);

        Set<Owner> findList = service.findAll();

        assertEquals(2, findList.size());
        then(ownerRepository).should().findAll();
    }

    @Test
    void findById() {
        Owner owner=Owner.builder().id(1L).lastName("Doe").build();
        given(ownerRepository.findById(anyLong())).willReturn(Optional.of(owner));

        Owner find=service.findById(1L);

        assertEquals("Doe",find.getLastName());
        then(ownerRepository).should().findById(anyLong());

    }

    @Test
    void save() {
        Owner owner=Owner.builder().id(1L).lastName("Doe").build();
        given(ownerRepository.save(any(Owner.class))).willReturn(owner);

        Owner savedOwner=service.save(new Owner());

        assertEquals(1L,savedOwner.getId());
        then(ownerRepository).should().save(any());
    }

    @Test
    void delete() {
        service.delete(new Owner());

        then(ownerRepository).should().delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        then(ownerRepository).should().deleteById(anyLong());
    }
}