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
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }
    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id).get();
    }

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

    @Override
    public boolean saveUser(HttpServletRequest request) {
        try{
            User user=User.builder()
                    .name(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .build();
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
