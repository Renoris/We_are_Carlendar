package study.bj.service;

import org.springframework.stereotype.Service;
import study.bj.data.PreCal;
import study.bj.data.RestObject;
import study.bj.data.User;

@Service
public class RestServiceImpl implements RestService{
    @Override
    public RestObject loginFailureObject() {
        return RestObject.builder().message("로그인이 되어있지 않습니다.").result(-1).build();

    }
}
