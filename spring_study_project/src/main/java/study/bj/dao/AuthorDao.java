package study.bj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.bj.data.Author;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Integer> {
    public List<Author> findByAllow(Integer id);
}
