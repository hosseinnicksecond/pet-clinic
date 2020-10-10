package home.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class ownersController {

    @RequestMapping({"","/list"})
    public String getOwnerList(){
        return "owners/list";
    }
}
