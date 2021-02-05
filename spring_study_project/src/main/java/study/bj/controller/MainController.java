package study.bj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import study.bj.data.Author;
import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.User;
import study.bj.service.AuthorService;
import study.bj.service.CalendarService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/mycalendar")
    public void saveCalendar(PreCal precal, HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("userinfo");
        calendarService.insertUpdateCalendar(precal, user);
        response.sendRedirect("/main");
    }

    @GetMapping(path = "/main")
    public ModelAndView main(HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        ModelAndView modelAndView = new ModelAndView("main");

        List<String> allowNameList = authorService.getAllowOkNameList(user.getName());
        List<DayCal> myCalList = calendarService.viewCalendar(user.getId());
        List<Author> waitAllowList = authorService.getAllowWaitList(user.getName());
        List<DayCal> otherCalList = calendarService.viewCalendarOther(allowNameList);

        modelAndView.addObject("calendarlist", myCalList);
        modelAndView.addObject("waitallowlist", waitAllowList);
        modelAndView.addObject("othercallist", otherCalList);
        return modelAndView;
    }

    @GetMapping(path = "/deleteCal")
    public void deleteCal(@RequestParam Integer id, HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("userinfo");
        calendarService.deleteCalendar(id, user.getId());
        response.sendRedirect("/main");
    }


    @GetMapping(path = "/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("userinfo", null);
        response.sendRedirect("/login");
    }
}
