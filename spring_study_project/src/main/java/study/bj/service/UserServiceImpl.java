package study.bj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.bj.dao.UserDao;
import study.bj.data.User;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User getUser(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public User[] getAllUser() {
        Object[] objects=userDao.findAll().toArray();
        return (User[]) objects;
    }

    @Override
    public void insertUpdateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }
}
