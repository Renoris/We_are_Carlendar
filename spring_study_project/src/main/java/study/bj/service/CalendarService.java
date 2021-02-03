package study.bj.service;

import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;

import java.util.List;

public interface CalendarService {
    public String insertUpdateCalendar(PreCal preCal, User user);
    public RestObject insertUpdateCalendarRest(PreCal preCal, User user);

    public List<DayCal> viewCalendar(Integer id);
    public RestObject viewCalendarRest(Integer id);
    public String deleteCalendar(Integer id, Integer userid);
    public RestObject deleteCalendarRest(Integer id, Integer userid);
    public List<DayCal> viewCalendarOther( List<String> lists);


}
