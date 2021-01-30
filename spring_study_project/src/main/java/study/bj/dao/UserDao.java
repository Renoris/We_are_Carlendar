package study.bj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.bj.data.User;

public interface UserDao extends JpaRepository<User, Integer> {
    public User findByName(String name);
}
