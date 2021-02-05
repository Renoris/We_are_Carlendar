package study.bj.service;

import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;

public interface RestService {
    public RestObject loginFailureObject();

    public RestObject viewCalendarRest(Integer id);

    public RestObject insertUpdateCalendarRest(PreCal preCal, User user);

    public RestObject deleteCalendarRest(Integer id, Integer userid);
}
