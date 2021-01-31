package study.bj.service;


import study.bj.data.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;

public interface UserService {

    public User getUserById(Integer id);
    public boolean logincheck(HttpServletRequest request, HttpSession session);
}
