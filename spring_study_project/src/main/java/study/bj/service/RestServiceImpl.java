package study.bj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    CalendarService calendarService;

    @Override
    public RestObject viewCalendarRest(Integer id) {
        RestObject restObject = new RestObject();

        restObject.setLists(calendarService.viewCalendar(id));

        return restObject;
    }

    @Override
    public RestObject deleteCalendarRest(Integer id, Integer userid) {
        Integer result = calendarService.deleteCalendar(id, userid);

        return makeRestObject(result);
    }

    @Override
    public RestObject insertUpdateCalendarRest(PreCal preCal, User user) {
        Integer result = calendarService.insertUpdateCalendar(preCal, user);

        return makeRestObject(result);
    }

    public RestObject makeRestObject(int result) {
        RestObject restObject = new RestObject();

        if (result != -1) {
            restObject.setResult(result);
            restObject.setMessage("성공했습니다.");
        } else {
            restObject.setResult(-1);
            restObject.setMessage("허용되지 않은 접근입니다.");
        }

        return restObject;
    }

    @Override
    public RestObject loginFailureObject() {
        return RestObject.builder().message("로그인이 되어있지 않습니다.").result(-1).build();

    }
}
