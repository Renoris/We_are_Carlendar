package study.bj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import study.bj.data.User;
import study.bj.service.AuthorService;
import study.bj.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorService authorService;

    @PostMapping("/allow")
    public ModelAndView allow(@RequestParam String name, HttpSession session) throws IOException {
        return allows("allow", name, session);
    }

    @GetMapping("/allowOk")
    public ModelAndView allowOk(@RequestParam String name, HttpSession session) throws IOException {
        return allows("allowOk", name, session);
    }

    @GetMapping("/deleteAllow")
    public ModelAndView deleteAllow(@RequestParam String name, HttpSession session) throws IOException {
        System.out.println("method:" + name);
        return allows("delete", name, session);
    }

    public ModelAndView allows(String command, String name, HttpSession session) {
        User user = (User) session.getAttribute("userinfo");
        User target = userService.getUserByName(name);
        ModelAndView modelAndView = new ModelAndView("allow");
        String message = "";
        if (command.equals("allow")) {
            message = authorService.goAllow(user, target);
        } else if (command.equals("allowOk")) {
            message = authorService.allowOk(user, target);
        } else if (command.equals("delete")) {
            message = authorService.deleteAllow(user, target);
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
