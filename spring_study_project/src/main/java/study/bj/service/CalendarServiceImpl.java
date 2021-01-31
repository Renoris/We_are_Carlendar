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
public class CalendarServiceImpl implements CalendarService{
    private final DayCalDao dayCalDao;

    @Override
    public List<DayCal> viewCalendar(Integer id) {
        return dayCalDao.findAllByUserid(id);
    }

    @Override
    public void deleteCalendar(Integer id, Integer userid) {
        DayCal dayCal=dayCalDao.findById(id).get();
        System.out.println("daycal id:"+dayCal.getId());
        System.out.println("user id:"+userid);
        if(dayCal.getUserid().equals(userid)){
            dayCalDao.deleteById(id);
        }
    }

    @Override
    public void insertUpdateCalendar(PreCal preCal, User user) {
        DayCal dayCal= DayCal.builder()
                .userid(user.getId())
                .name(user.getName())
                .title(preCal.getTitle())
                .content(preCal.getContent())
                .startdate(preCal.getStartdate())
                .starttime(preCal.getStarttime())
                .enddate(preCal.getEnddate())
                .endtime(preCal.getEndtime())
                .build();
        if(preCal.getId()!=-1){
            System.out.println(preCal.getId());
            dayCal.setId(preCal.getId());
        }
        dayCalDao.save(dayCal);
    }

    public List<DayCal> viewCalendarOther(List<String> lists){
        List<DayCal> otherlists=new ArrayList<>();
        for(String name:lists){
            otherlists.addAll(dayCalDao.findAllByName(name));
        }
        return otherlists;
    }
}
