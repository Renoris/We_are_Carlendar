package study.bj;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.bj.dao.DayCalDao;

public class test {

    private static DayCalDao dayCalDao;
    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("study.bj");
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml");
//        ApplicationContext applicationContext = new GenericGroovyApplicationContext("daoFactory.groovy");
        dayCalDao = applicationContext.getBean("dayCalDao",DayCalDao.class);
    }

    @Test
    public void get(){
        System.out.println(dayCalDao.findAllByUserid(1));
    }
}
