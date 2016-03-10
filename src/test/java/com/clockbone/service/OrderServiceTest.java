package com.clockbone.service;

import com.clockbone.domain.Item;
import com.clockbone.mapper.ItemMapper;
import com.clockpone.domain.Main;
import com.clockpone.mapper.MainMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qinjun on 2016/3/10.
 */
public class OrderServiceTest extends BaseTest {

    @Autowired
    public MainMapper mainMapper;

    @Autowired
    public ItemMapper itemMapper;

    @Test
    public void MapperTest(){

        Main user = mainMapper.getUserByNameTest("admin");
        System.out.println(user);

        List<Item> list =  itemMapper.findAll();

        System.out.println(list);

    }
}
