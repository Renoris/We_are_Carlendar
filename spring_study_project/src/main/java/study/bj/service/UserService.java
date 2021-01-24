package study.bj.service;


import study.bj.data.User;

import java.util.ArrayList;

public interface UserService {
    public User getUser(Integer id);
    public User[] getAllUser();
    public void insertUpdateUser(User user);
    public void deleteUser(Integer id);
}
