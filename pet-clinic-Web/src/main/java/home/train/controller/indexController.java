package home.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping({"","/"})
    public String index(){
        return "index";
    }

    @RequestMapping("/oups")
    public String getOOpsPage(){
        return "notImplement";
    }
}
