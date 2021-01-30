package study.bj.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import study.bj.dao.UserDao;
import study.bj.data.User;
import study.bj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public void fuckup(HttpServletResponse response,HttpSession session) throws IOException {
        if(session.getAttribute("userinfo")!=null){
            response.sendRedirect("/main");
        }else{
            response.sendRedirect("/login");
        }
    }


    @GetMapping(path = "/login")
    public ModelAndView login(HttpServletResponse response,HttpSession session) throws IOException {
        if(session.getAttribute("userinfo")!=null){
            response.sendRedirect("/main");
        }
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("msg","");
        return modelAndView;
    }

    @PostMapping(path ="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        if(!userService.logincheck(request,session)){
            ModelAndView modelAndView=new ModelAndView("login");
            modelAndView.addObject("msg","잘못된 입력값 입니다");
            return modelAndView;
        }else{
            ModelAndView modelAndView=new ModelAndView("main");
            response.sendRedirect("/main");
            return modelAndView;
        }
    }

    @GetMapping(path = "/main")
    public ModelAndView main(){
        ModelAndView modelAndView=new ModelAndView("main");
        return modelAndView;
    }

}
