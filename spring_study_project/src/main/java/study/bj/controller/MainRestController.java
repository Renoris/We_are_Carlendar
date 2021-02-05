package study.bj.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;
import study.bj.service.CalendarService;
import study.bj.service.RestService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MainRestController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private RestService restService;

    @GetMapping("/CalRestView")
    public @ResponseBody
    RestObject View(HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        RestObject restObject;
        if (user == null) {
            restObject = restService.loginFailureObject();
        } else {
            restObject = restService.viewCalendarRest(user.getId());
        }
        return restObject;
    }

    @PostMapping("/CalRestSave")
    public @ResponseBody
    RestObject insertUpdate(PreCal preCal, HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        RestObject restObject;
        if (user == null) {
            restObject = restService.loginFailureObject();
        } else {
            restObject = restService.insertUpdateCalendarRest(preCal, user);
        }
        return restObject;
    }

    @PostMapping("/CalRestDelete")
    public @ResponseBody
    RestObject delete(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        RestObject restObject;
        if (user == null) {
            restObject = restService.loginFailureObject();
        } else {
            restObject = restService.deleteCalendarRest(id, user.getId());
        }
        return restObject;
    }
}
