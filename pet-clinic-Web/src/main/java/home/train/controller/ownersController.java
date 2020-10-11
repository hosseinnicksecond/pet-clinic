package home.train.controller;

import home.train.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class ownersController {

    private final OwnerService service;

    public ownersController(OwnerService service) {
        this.service = service;
    }

    @RequestMapping({"","/list"})
    public String getOwnerList(Model model){

        model.addAttribute("owners",service.findAll());
        return "owners/list";
    }
}
