package life.ajie.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloController {
    @GetMapping("/")
    public static String index(){
        return "index";
    }
}
