package home.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class vetsController {

    @RequestMapping({"/vets","/vets/list"})
    public String vetList(){
        return "vets/list";
    }
}
