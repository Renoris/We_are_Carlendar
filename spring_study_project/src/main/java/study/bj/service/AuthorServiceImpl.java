package study.bj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.bj.dao.AuthorDao;
import study.bj.data.Author;
import study.bj.data.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorDao authorDao;

    @Override
    public String allowOk(User my, User target) {
        String message;
        if(target==null){
            message="유저가 존재 하지 않습니다";
        }else{
            message="요청을 수락하였습니다.";
            Author author=authorDao.findByUsernameAndTarget(target.getName(),my.getName());
            author.setAllow(1);
            authorDao.save(author);
        }
        return message;
    }

    @Override
    public String goAllow(User my, User target) {
        String message;
        if(target==null){
            message="유저가 존재 하지 않습니다";
        }else{
            message="요청을 보냈습니다.";
            Author author=Author.builder().username(my.getName()).target(target.getName()).allow(0).build();
            authorDao.save(author);
        }
        return message;

    }

    @Override
    public String deleteAllow(User my, User target) {
        String message;
        if(target==null){
            message="유저가 존재 하지 않습니다";
        }else{
            message="요청이 삭제되었습니다";
            Author author=authorDao.findByUsernameAndTarget(target.getName(),my.getName());
            authorDao.deleteById(author.getId());
        }
        return message;
    }

    @Override
    public List<Author> getAllowWaitList(String name) {
        List<Author> lists=authorDao.findALLByAllowAndTarget(0,name);
        return lists;
    }

    @Override
    public List<String> getAllowOkNameList(String name) {
        List<Author> lists=authorDao.findALLByAllowAndTarget(1,name);
        List<String> namelist=new ArrayList<>();
        for(Author list:lists){
            namelist.add(list.getUsername());
        }
        return namelist;
    }


}
