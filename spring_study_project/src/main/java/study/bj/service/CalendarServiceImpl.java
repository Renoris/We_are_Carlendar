package study.bj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.bj.dao.DayCalDao;
import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final DayCalDao dayCalDao;

    @Override
    public List<DayCal> viewCalendar(Integer id) {
        return dayCalDao.findAllByUserid(id);
    }

    @Override
    public Integer deleteCalendar(Integer id, Integer userid) {
        DayCal dayCal = dayCalDao.findById(id).get();

        if (dayCal.getUserid().equals(userid)) {
            dayCalDao.deleteById(id);
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public Integer insertUpdateCalendar(PreCal preCal, User user) {
        DayCal dayCal;
        if (preCal.getId() != -1) {//update
            dayCal = dayCalDao.findById(preCal.getId()).get();

            if (!dayCal.getId().equals(preCal.getId())) {
                return -1;
            } else {
                insertData(dayCal, preCal);
                return dayCalDao.save(dayCal).getId();
            }

        } else {//insert
            dayCal = DayCal.builder()
                    .userid(user.getId())
                    .name(user.getName())
                    .build();

            insertData(dayCal, preCal);
            System.out.println("저장될 데이터는:"+dayCal.toString());
            return dayCalDao.save(dayCal).getId();
        }
    }

    public List<DayCal> viewCalendarOther(List<String> lists) {
        List<DayCal> otherLists = new ArrayList<>();

        for (String name : lists) {
            otherLists.addAll(dayCalDao.findAllByName(name));
        }

        return otherLists;
    }


    public void insertData(DayCal findCal, PreCal preCal) { //call by reference
        findCal.setContent(preCal.getContent());
        findCal.setTitle(preCal.getTitle());
        findCal.setStartdate(preCal.getStartdate());
        findCal.setStarttime(preCal.getStarttime());
        findCal.setEnddate(preCal.getEnddate());
        findCal.setEndtime(preCal.getEndtime());
    }
}
