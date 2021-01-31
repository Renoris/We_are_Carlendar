package study.bj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import study.bj.dao.UserDao;
import study.bj.data.User;
import study.bj.service.UploadService;
import study.bj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;

    @Autowired// userService라는 interface를 구현한 클래스를 주입
    private UserService userService;

    @Autowired//UploadService란느 interface를 구현한 클래스를 주입
    private UploadService uploadService;

    //method로 받을수 있는 인자값 리스트
    //request,response,session
    @RequestMapping("/test1")
    public void testing(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    }

    //pathVarivable 사용법
    @GetMapping(path = "/test2/{id}")
    public ModelAndView testing2(@PathVariable("id") int id) {
        return new ModelAndView("test");
    }

    //requestparam
    @RequestMapping("/test3")
    public void testing3(@RequestParam("id") int id, @RequestParam(value = "pw", required = false, defaultValue = "1234") String pw) {
    }

    //쿠키값
    @RequestMapping("/test4")
    public void testing4(@CookieValue("id") String id) {
    }

    //Map
    @RequestMapping("/test5")
    public void testing5(@RequestParam("id") int id, Map map) {
        map.put("user", userDao.findById(id).get());
    }
    //Model map

    @RequestMapping("/test6")
    public void testing6(@RequestParam("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userDao.findById(id).get());
    }

    //modelAttribute
    @RequestMapping("/test7")
    public void testing7(@ModelAttribute User user) {
        int id = user.getId();
        User user2 = userDao.findById(user.getId()).get();
        user.setName(user2.getName());
    }

    @PostMapping("/insert")
    public void insert(@ModelAttribute User user) {
        System.out.println(user.getName());
    }

    //    @RequestMapping
    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.findById(id).get();
    }

    @RequestMapping("/exception")
    public void exception() {
        throw new RuntimeException("어이쿠!");
    }


//    @RequestMapping(path="/upload", method = RequestMethod.GET)
//    public void upload(){
//
//    }
//
//    @RequestMapping(path="/upload",method = RequestMethod.POST)
//    public ModelAndView upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
//        uploadService.upload(file,request);
//        //----------------------파일 저장끝
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("url","/images"+file.getOriginalFilename());
//        return modelAndView;
//    }

    @RequestMapping(path = "/upload2", method = RequestMethod.GET)
    public ModelAndView upload2() {
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("url", "");//null을 넣으면 안된다 시부레
        return modelAndView;
    }

    @RequestMapping(path = "/upload2", method = RequestMethod.POST)
    public ModelAndView upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        uploadService.upload(file, request);
        //----------------------파일 저장끝
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("url", "/images" + file.getOriginalFilename());
        return modelAndView;
    }


    @ExceptionHandler(Exception.class) //exception이 떨어졌을때 자체가 이쪽으로 핸들링 하는것이 HandlerExceptionResolver
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}
