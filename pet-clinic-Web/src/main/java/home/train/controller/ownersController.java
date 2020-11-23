package home.train.controller;

import home.train.model.Owner;
import home.train.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RequestMapping("/owners")
@Controller
public class ownersController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public ownersController(OwnerService service) {
        this.ownerService = service;
    }

    @InitBinder
    public void setAllowFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @GetMapping({"/owners", "/owners/list"})
//    public String getOwnerList(Model model) {
//        model.addAttribute("selections", ownerService.findAll());
//        return "owners/ownersList";
//    }

    @GetMapping("/find")
    public String showFindPage(Model model) {
        Owner owner = new Owner();
//        owner.setLastName("kk");
        model.addAttribute("owner", owner);
        return "owners/findOwners";
    }

    @GetMapping("/form")
    public String findOwnerProcess(Owner owner, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        Set<Owner> owners = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if (owners.isEmpty()) {
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            return "redirect:/owners/" + owners.iterator().next().getId();
        } else {
            log.info("return size {}",owners.size());
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }

    }


    @GetMapping("/{ownerId}")
    public ModelAndView getOwner(@PathVariable("ownerId") String id) {
        ModelAndView mv = new ModelAndView("owners/ownerDetails");
        mv.addObject(ownerService.findById(Long.valueOf(id)));
        return mv;
    }

    @GetMapping("/new")
    public String showUpdateForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processNewOwnerForm(@Valid Owner owner, BindingResult result){

        if(result.hasErrors()){
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }else {
            Owner savedOwner=ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String showFormForUpdate(@PathVariable("ownerId") String ownerId,Model model){
        model.addAttribute("owner",ownerService.findById(Long.valueOf(ownerId)));
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processFormForUpdate(@Valid Owner owner,BindingResult result,
                                       @PathVariable String ownerId){
        if (result.hasErrors()){
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        }
        owner.setId(Long.valueOf(ownerId));//we do that cause in setAllowFields() above we did not allow
                                           // model to touch getting id
        Owner savedOwner=ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }
}
