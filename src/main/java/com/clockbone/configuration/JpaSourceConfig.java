package com.clockbone.configuration;

import com.clockbone.jpadao.ItemRepository;
import com.clockbone.jpadao.MyBaseRepository;
import com.clockbone.jpadao.PersonRepository;
import com.clockbone.jpadao.UserRepository;
import com.clockbone.jpadomain.Person;
import com.clockbone.jpadomain.Test;
import com.clockbone.jpadomain.UserTest;
import com.clockbone.service.OrderService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.*;

/**
 * Created by qinjun on 2016/3/10.
 * 配置jpa数据源
 */
@Configuration()
@ComponentScan("")
@ImportResource(
        {     "classpath:datasource-config.xml"/*,
                "classpath:applicationContext.xml"*/ //运行jetty时，需要把这里加载的applicationContext.xml文注释，因为只能加载一个app.xml配置文件
                                                     //运行这里的测试方法再打开注释
        })
@EnableJpaRepositories("com.clockbone.jpadao")
@EnableTransactionManagement
public class JpaSourceConfig {

    @Autowired
    DataSource dataSource;

    //@Bean负责注册bean对象
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        //设置扫描的jpa的model类为jpadomain包下的
        lef.setPackagesToScan("com.clockbone.jpadomain");
        //配置orm，orm配置持久层是jpa
        lef.setMappingResources("orm.xml");
        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put(Environment.HBM2DDL_AUTO, "none");
        lef.setJpaPropertyMap(jpaProperties);
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaSourceConfig.class);
        //testTransactional(context);

        //itemRepositoryTest(context);
        //pagingAndSortingRepositoryTest(context);

        //itemRepositoryTest(context);
        //userRep(context);
        persionRepTest(context);
        context.close();
    }



    public static void base(AbstractApplicationContext context){

    }
    //一个继承CrudRepository类的测试
    public static void persionRepTest(AbstractApplicationContext context){
        PersonRepository re = context.getBean(PersonRepository.class);
        Person p = new Person();
        p.setName("其它角色");
        p.setDescription("biaoshu");
        p.setRoleKey("juesheguanjian");
        //这里类似hibernate,Person类中会配置@Entity，自动生成sql语句
        Person rePerson = re.save(p);
        System.out.println(rePerson);

    }



    public static void userRep(AbstractApplicationContext context){
        UserRepository repository = context.getBean(UserRepository.class);

        //List<UserTest> lastnameorfirestname = repository.findByLastnameOrFirstname("jack", "O'Brian");

        //repository.findAll();

       /*
       List<UserTest> list = repository.getJackUserTest(1);
        List<UserTest> list1 = repository.getJackUserTest(1);
        List<UserTest> list2 = repository.getJackUserTest(1);*/

        List<UserTest> test = repository.test("Bauer");
        System.out.println(test);

        repository.update(1,"usertest");
        //System.out.println(back);

    }

    //分页的测试
    public static void pagingAndSortingRepositoryTest(AbstractApplicationContext context){
        PagingAndSortingRepository repository = context.getBean(PagingAndSortingRepository.class);
        Page<Person> persons = repository.findAll(new PageRequest(0, 20 , Sort.Direction.DESC,"id"));

    }
    public static void itemRepositoryTest(AbstractApplicationContext context){
        ItemRepository repository = context.getBean(ItemRepository.class);
        //repository.save(entity);

        // save a couple of customers
       /* repository.save(new Test("Jack", "Bauer"));
        repository.save(new Test("Chloe", "O'Brian"));
        repository.save(new Test("Kim", "Bauer"));
        repository.save(new Test("David", "Palmer"));
        repository.save(new Test("Michelle", "Dessler"));
        List<Test> list = repository.findAll();*/

        //测试自有方法
        List<Test> test1 = repository.findByKey("Jack");
        List<Test> test2 = repository.findByName("Jack");
        //测试自定义sql语句方法
        Boolean b = repository.exists(1);

        List<Test> test = repository.findByFirstnameEndsWith("Bauer");

        System.out.println(test);

    }

    public static void testTransactional(AbstractApplicationContext context ){
        final OrderService orderService=context.getBean(OrderService.class);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
    public static void testcreateOrder(AbstractApplicationContext context ){

        final OrderService orderService=context.getBean(OrderService.class);

	    	   /* ItemRepository itemRep=context.getBean(ItemRepository.class);
	    		Item item = itemRep.findByKey(itemKey);
	    		item.setTotal(115);
	    		item.setSoldNum(114);
	    		itemRep.save(item);*/
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
