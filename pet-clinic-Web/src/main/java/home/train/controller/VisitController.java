package home.train.controller;

import home.train.model.Pet;
import home.train.model.Visit;
import home.train.service.PetService;
import home.train.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit initVisit(@PathVariable("petId")String petId, Model model){
        Pet pet=petService.findById(Long.valueOf(petId));
        pet.setVisits(visitService.findVisitByPetId(Long.valueOf(petId)));
        model.addAttribute("pet",pet);
        Visit visit= new Visit();
        pet.addVisit(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method initVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@PathVariable("ownerId") String ownerId,@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }
        else {
            Visit savedVisit=visitService.save(visit);
            log.info("saved visit pet id ===>{}",savedVisit.getPet().getId());
            log.info("saved visit owner id ===>{}",savedVisit.getPet().getOwner().getId());
            return "redirect:/owners/"+ownerId;
        }
    }
}
