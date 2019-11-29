package life.ajie.community.controller;

import life.ajie.community.dto.QuestionDTO;
import life.ajie.community.mapper.QuestionMapper;
import life.ajie.community.mapper.UserMapper;
import life.ajie.community.model.Question;
import life.ajie.community.model.User;
import life.ajie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class PublishController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id")Integer id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value="title",required = false)String title,
            @RequestParam(value="description",required = false)String description,
            @RequestParam(value="tag",required = false)String tag,
            @RequestParam(value="id",required = false)Integer id,

            HttpServletRequest request,
            Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");


        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user=(User)request.getSession().getAttribute("user");

        if(user==null){
            model.addAttribute("error","用户未登录");
            System.out.println("不成功");
            return  "publish";

        }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
