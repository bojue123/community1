package life.ajie.community.controller;

import javafx.scene.control.Pagination;
import life.ajie.community.dto.PaginationDTO;
import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.Notification;
import life.ajie.community.model.User;
import life.ajie.community.service.NotificationService;
import life.ajie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action,
                          HttpServletRequest request ,
                          Model model,
                          @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size){
        User user=(User)request.getSession().getAttribute("user");


        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的发布");
            PaginationDTO paginationDTO=questionService.list(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action)){

            PaginationDTO paginationDTO=notificationService.list(user.getId(),page,size);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
