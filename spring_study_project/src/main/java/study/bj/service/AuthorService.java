package study.bj.service;


import study.bj.data.Author;
import study.bj.data.User;

import java.util.List;

public interface AuthorService {

    public List<Author> getAllowWaitList(String name);

    public String goAllow(User my,User target);

    public String allowOk(User my,User target);

    public List<String> getAllowOkNameList(String name);

    public String deleteAllow(User my,User target);
}
