package home.train.controller;

import home.train.model.Owner;
import home.train.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ownersControllerTest {

    @Mock
    OwnerService Service;

    @InjectMocks
    ownersController controller;

    MockMvc mockMvc;

    Set<Owner> ownerSet;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();

        ownerSet= new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).lastName("Doe").build());
        ownerSet.add(Owner.builder().id(2L).lastName("Smith").build());

    }

//    @Test
//    void getOwnerList() throws Exception {
//        when(Service.findAll()).thenReturn(ownerSet);
//
//        mockMvc.perform(get("/owners/"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("owners"))
//                .andExpect(model().attribute("owners",hasSize(2)))
//                .andExpect(view().name("owners/list"));
//
//        verify(Service).findAll();
//    }

    @Test
    void getOwner() throws Exception {
        Owner owner= new Owner();
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setFirstName("john");
        when(Service.findById(1L)).thenReturn(owner);

        mockMvc.perform(get("/owners/{ownersId}",1L))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"));

        verify(Service).findById(anyLong());
    }

    @Test
    void getFindPage() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void testFindOwnerOne() throws Exception {
        Set<Owner> owners= new HashSet<>();
        owners.add(Owner.builder().id(1L).lastName("Doe").build());
        when(Service.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners/form"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void testFindOwnersMany() throws Exception {
        Set<Owner> owners= new HashSet<>();
        owners.add(Owner.builder().id(1L).lastName("Doe").build());
        owners.add(Owner.builder().id(2L).lastName("public").build());

        when(Service.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners/form"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));
    }

    @Test
    void showFormForNewOwners() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFormNewOwners() throws Exception {
        when(Service.save(any())).thenReturn(Owner.builder().id(2L).build());
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"));

        verify(Service).save(any());
    }

    @Test
    void showFormForUpdate()throws Exception {
        when(Service.findById(anyLong())).thenReturn(Owner.builder().id(2L).build());

        mockMvc.perform(get("/owners/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(Service).findById(2L);
    }

    @Test
    void processForForUpdate() throws Exception {
        when(Service.save(any())).thenReturn(Owner.builder().id(2L).build());

        mockMvc.perform(post("/owners/{owners}/edit",2))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/2"));

        verify(Service).save(any());
    }
}