package study.bj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.bj.data.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Integer> {
    public List<Author> findALLByAllow(Integer id);

    public List<Author> findALLByAllowAndTarget(Integer allow, String target);

    public Author findByUsernameAndTarget(String username, String target);
}
