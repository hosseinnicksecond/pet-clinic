package home.train.controller;

import home.train.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class vetsController {

    private final VetService service;


    public vetsController(VetService service) {
        this.service = service;
    }

    @RequestMapping({"/vets","vets/list","/vets.html"})
    public String vetList(Model model){

        model.addAttribute("vets",service.findAll());
        return "vets/list";
    }
}
