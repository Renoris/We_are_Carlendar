package study.bj.service;

import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.User;

import java.util.List;

public interface CalendarService {
    public Integer insertUpdateCalendar(PreCal preCal, User user);

    public List<DayCal> viewCalendar(Integer id);

    public Integer deleteCalendar(Integer id, Integer userid);

    public List<DayCal> viewCalendarOther(List<String> lists);


}
