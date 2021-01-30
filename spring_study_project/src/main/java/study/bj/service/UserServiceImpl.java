package study.bj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.bj.dao.UserDao;
import study.bj.data.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public boolean logincheck(HttpServletRequest request, HttpSession session) {
        User findUser = userDao.findByName(request.getParameter("username"));
        if (findUser == null || !findUser.getPassword().equals(request.getParameter("password"))) {
            return false;
        } else {
            User sessionUser = User.builder().id(findUser.getId()).name(findUser.getName()).build();
            session.setAttribute("userinfo", sessionUser);
            return true;
        }
    }
}
