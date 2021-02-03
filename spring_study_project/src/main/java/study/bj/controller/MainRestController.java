package study.bj.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;
import study.bj.service.CalendarService;
import study.bj.service.RestService;
import study.bj.service.UserService;

import javax.naming.Context;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private RestService restService;

    @GetMapping("/CalRestView")
    public @ResponseBody RestObject View(HttpSession session){
        User user=(User) session.getAttribute("userinfo");
        System.out.println(user);
        RestObject restObject;
        if(user==null){
            restObject=restService.loginFailureObject();
        }else{
            restObject=calendarService.viewCalendarRest(user.getId());
        }
        return restObject;
    }

    @PostMapping("/CalRestSave")
    public @ResponseBody
    RestObject insertUpdate(PreCal preCal, HttpSession session){
        User user=(User) session.getAttribute("userinfo");
        RestObject restObject;
        if(user==null){
            restObject=restService.loginFailureObject();
        }else{
            restObject=calendarService.insertUpdateCalendarRest(preCal, user);
        }
        return restObject;
    }

    @PostMapping("/CalRestDelete")
    public @ResponseBody RestObject delete(Integer id, HttpSession session){
        System.out.println("delete 시작? id:"+id);
        User user=(User) session.getAttribute("userinfo");
        RestObject restObject;
        if(user==null){
            restObject=restService.loginFailureObject();
        }else{
            restObject=calendarService.deleteCalendarRest(id,user.getId());
        }
        return restObject;
    }

}
