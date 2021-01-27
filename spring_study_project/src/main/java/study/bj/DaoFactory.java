package study.bj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

//bean 관리
@Configuration
@EnableJpaRepositories(basePackages = "study.bj", entityManagerFactoryRef = "entityManagerFactoryBean")
//Enable Jpa Repositories-repository라고 등록된 녀석을 찾아서 bean으로 등록시키는 역할
//entity managerfactoryref-실제 jpa를 구현한 녀석은 무엇이냐 라고 물어보는걸 알려주는역할 밑에 빈으로 정의해줘야함
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean //현재환경의 entityManager를 setting 해주는 역할-jpa
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource()); //어떤 데이터베이스를 연결한 데이터소스를 쓸래? 이미 정의되어있는 dataSource를 쓰겟다
        entityManagerFactoryBean.setPackagesToScan("study.bj");//@Entity를 가진녀석들을 어디서 찾을래?

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);//어떤 밴더(jpa를 구현한녀석)?

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //jpa를 find를 한다가정하면 hibernate를 query로 바꿀때 각 vender에 맞게 sql을 바꿔주는 역할

        entityManagerFactoryBean.setJpaProperties(jpaProperties);//jpa에서 활용할 jpaproperty

        return entityManagerFactoryBean;
    }

    @Bean
    //jpa를 선언할때는 transaction을 꼭 선언하도록 되어있음
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

//    //freemarker bean
//    @Bean
//    public ViewResolver viewResolver(){
//        FreeMarkerViewResolver resolver=new FreeMarkerViewResolver();
//        resolver.setCache(false);
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        return resolver;
//    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer=new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/freemarker");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }
    @Bean
    public JdbcTemplate jdbcContext() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}

