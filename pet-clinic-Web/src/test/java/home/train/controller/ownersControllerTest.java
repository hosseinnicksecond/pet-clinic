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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        when(Service.findAll()).thenReturn(ownerSet);
    }

    @Test
    void getOwnerList() throws Exception {
        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",hasSize(2)))
                .andExpect(view().name("owners/list"));

        verify(Service).findAll();
    }

}