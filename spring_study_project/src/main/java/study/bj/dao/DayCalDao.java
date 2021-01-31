package study.bj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.bj.data.DayCal;

import java.util.List;

public interface DayCalDao extends JpaRepository<DayCal, Integer> {
    public List<DayCal> findAllByUserid(Integer id);
}
