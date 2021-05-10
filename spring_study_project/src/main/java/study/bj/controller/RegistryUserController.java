package study.bj.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import study.bj.data.User;
import study.bj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@Controller
public class RegistryUserController {

    @Autowired
    UserService userService;

    @GetMapping("/registry")
    public ModelAndView viewPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg","");
        return modelAndView;
    }

    @PostMapping("/registry")
    public String registryUser(HttpServletRequest request, HttpServletResponse response){
        User user=userService.getUserByName(request.getParameter("username"));

        if(user==null){
            boolean issave=userService.saveUser(request);

            if(issave){
                request.setAttribute("msg","가입되었습니다");
            }else{
                request.setAttribute("msg","생성이불가능합니다");
            }
            return "login";
        }else{
            request.setAttribute("msg","이미 있는 사용자입니다.");
        }
        return "registry";
    }

}
