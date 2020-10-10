package home.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class vetsController {

    @RequestMapping({"","/list"})
    public String vetList(){
        return "vets/list";
    }
}
