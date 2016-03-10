package com.clockbone.configuration;

import com.clockbone.domain.Order;
import com.clockbone.jpadao.ItemRepository;
import com.clockbone.jpadomain.Test;
import com.clockbone.service.OrderService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.Visitor;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Nullable;
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
        {     "classpath:datasource-config.xml",
                "classpath:applicationContext.xml"
        })
@EnableJpaRepositories("com.clockbone.jpadao")
@EnableTransactionManagement
public class JpaSourceConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.clockbone.jpadomain");
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

        ItemRepository repository = context.getBean(ItemRepository.class);
        //repository.save(entity);

        // save a couple of customers
        repository.save(new Test("Jack", "Bauer"));
        repository.save(new Test("Chloe", "O'Brian"));
        repository.save(new Test("Kim", "Bauer"));
        repository.save(new Test("David", "Palmer"));
        repository.save(new Test("Michelle", "Dessler"));
        List<Test> list = repository.findAll();

        List<Test> test1 = repository.findByKey("Jack");
        List<Test> test2 = repository.findByName("Jack");

        Boolean b = repository.exists(1);


        context.close();
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
