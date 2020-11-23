package home.train.controller;

import home.train.model.Owner;
import home.train.model.PetType;
import home.train.service.OwnerService;
import home.train.service.PetService;
import home.train.service.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class petController {
    
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public petController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }
    
    @ModelAttribute("types")
    public Collection<PetType> getPetTypeList(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner getOwner(@PathVariable("ownerId")String ownerId){
        return ownerService.findById(Long.valueOf(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinding(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @InitBinder("pet")
//    public void initPetBinder(WebDataBinder dataBinder){
//        dataBinder.setValidator(new PetValidator());
//    }
}
