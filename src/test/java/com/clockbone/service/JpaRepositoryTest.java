package com.clockbone.service;

import com.clockbone.jpadao.ItemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by qinjun on 2016/3/11.
 */
public class JpaRepositoryTest extends JpaBaseTest {

    /*@Autowired
    ItemRepository repository;*/

    public static void main(String[] args){
        ItemRepositoryTest();
    }

    public static void ItemRepositoryTest(){

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaRepositoryTest.class);
        //testTransactional(context);

        ItemRepository repository = context.getBean(ItemRepository.class);
        repository.findByKey("Jack");
        repository.findByKey("Jack");
        repository.findByKey("Jack");
        repository.findByKey("Jack");
        repository.findByKey("Jack");


    }
}
