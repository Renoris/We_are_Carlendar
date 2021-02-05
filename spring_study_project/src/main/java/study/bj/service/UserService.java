package study.bj.service;


import study.bj.data.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    public User getUserByName(String name);

    public User getUserById(Integer id);

    public boolean logincheck(HttpServletRequest request, HttpSession session);
}
