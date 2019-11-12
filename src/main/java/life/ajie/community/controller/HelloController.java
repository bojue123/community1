package life.ajie.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name")String name, Model model) {
        model.addAttribute("name",name);
        //字符串中的name对应html中的变量名为name，这里是把参数名为name的变量赋值给hello.html的name变量
        return "hello";
        //采集什么
    }
}
