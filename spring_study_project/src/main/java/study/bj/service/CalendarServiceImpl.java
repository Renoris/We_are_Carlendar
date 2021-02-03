package study.bj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.bj.dao.DayCalDao;
import study.bj.data.DayCal;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
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
    public RestObject viewCalendarRest(Integer id) {
        RestObject restObject=new RestObject();
        restObject.setLists(dayCalDao.findAllByUserid(id));
        restObject.setMessage("성공");
        restObject.setResult(-1);
        return restObject;
    }

    @Override
    public String deleteCalendar(Integer id, Integer userid) {
        String message="";
        DayCal dayCal=dayCalDao.findById(id).get();
        if(dayCal.getUserid().equals(userid)){
            dayCalDao.deleteById(id);
            message="삭제 되었습니다.";
        }else{
            message="소유주가 아닙니다";
        }
        return message;
    }

    @Override
    public RestObject deleteCalendarRest(Integer id, Integer userid) {
        RestObject restObject=new RestObject();
        if(dayCalDao.findById(id).get().getUserid().equals(userid)){
            dayCalDao.deleteById(id);
            restObject.setMessage("삭제 되었습니다");
            restObject.setResult(id);
        }else{
            restObject.setMessage("적합하지 않습니다");
            restObject.setResult(-1);
        }
        return restObject;

    }

    @Override
    public String insertUpdateCalendar(PreCal preCal, User user) {
        String message = "저장 되었습니다.";
        if(preCal.getId()!=-1){//update
            DayCal findCal=dayCalDao.findById(preCal.getId()).get();
            if(!findCal.getId().equals(preCal.getId())){
                message = "올바르지 않은 접근입니다";
            }else{
                insertdata(findCal,preCal);
                message="수정 성공";
                dayCalDao.save(findCal);
            }
        }else{//insert
            DayCal dayCal=DayCal.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .build();
            insertdata(dayCal,preCal);
            dayCalDao.save(dayCal);
        }
        return message;
    }

    @Override
    public RestObject insertUpdateCalendarRest(PreCal preCal, User user) {
        String message = "저장 되었습니다.";
        RestObject restObject=new RestObject();
        if(preCal.getId()!=-1){//update
            DayCal findCal=dayCalDao.findById(preCal.getId()).get();
            if(!findCal.getId().equals(preCal.getId())){
                message = "올바르지 않은 접근입니다";
                restObject.setMessage(message);
                restObject.setResult(-1);
            }else{
                insertdata(findCal,preCal);
                restObject.setMessage("수정 되었습니다.");
                restObject.setResult(dayCalDao.save(findCal).getId());
            }
        }else{//insert
            DayCal dayCal=DayCal.builder()
                    .userid(user.getId())
                    .name(user.getName())
                    .build();
            insertdata(dayCal,preCal);
            dayCalDao.save(dayCal);
            restObject.setResult(dayCalDao.save(dayCal).getId());
            restObject.setMessage(message);
        }
        return restObject;
    }

    public void insertdata(DayCal findCal, PreCal preCal){ //call by reference
        findCal.setContent(preCal.getContent());
        findCal.setTitle(preCal.getTitle());
        findCal.setStartdate(preCal.getStartdate());
        findCal.setStarttime(preCal.getStarttime());
        findCal.setEnddate(preCal.getEnddate());
        findCal.setEndtime(preCal.getEndtime());
    }
    public List<DayCal> viewCalendarOther(List<String> lists){
        List<DayCal> otherlists=new ArrayList<>();
        for(String name:lists){
            otherlists.addAll(dayCalDao.findAllByName(name));
        }
        return otherlists;
    }
}
