package home.train.controller;

import home.train.model.Owner;
import home.train.model.Pet;
import home.train.model.PetType;
import home.train.service.OwnerService;
import home.train.service.PetService;
import home.train.service.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class petController {

    public static final String PET_FORM = "pets/createOrUpdatePetForm";
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
        Owner owner= ownerService.findById(Long.valueOf(ownerId));
        log.info("owner pets size == {}",owner.getPets().size()+1);
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

    @GetMapping("/pets/new")
    public String showFormNewPet(Owner owner,Model model){
        Pet pet= new Pet();
        owner.addPet(pet);
        model.addAttribute("pet",pet);
        return PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return PET_FORM;
        }
        else {
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String showFormUpdatePet(@PathVariable String petId, Model model){
        Pet pet=this.petService.findById(Long.valueOf(petId));
        log.info("pet owner name ***===> {}",pet.getOwner().getFirstName());
        model.addAttribute("pet",pet);
        return PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.put("pet", pet);
            return PET_FORM;
        }
        else {

            Pet savedPet=petService.updatePet(owner,pet);
            return "redirect:/owners/"+savedPet.getOwner().getId();
        }
    }
}
