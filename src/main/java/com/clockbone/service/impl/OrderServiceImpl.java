package com.clockbone.service.impl;

import com.clockbone.domain.Item;
import com.clockbone.domain.Order;
import com.clockbone.domain.OrderItem;
import com.clockbone.mapper.ItemMapper;
import com.clockbone.service.OrderService;
import com.clockbone.vo.CartItem;
import com.clockbone.vo.Result;
import com.clockpone.domain.Main;
import com.clockpone.mapper.MainMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by qinjun on 2016/2/24.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired(required = false)
    private MainMapper mainMapper;

    private static final Object itemlock = new Object();

    private static volatile HashMap<Integer, Item> itemList = null;

    @Override
    public Order createOrder(List<CartItem> items, String payType, Result result) {
        return createOrderProcess(items,payType,null,null,result);
    }

    private Order createOrderProcess(List<CartItem> items,String payType,String userName,String popu,Result result){

        List<Item> list1 = itemMapper.findAll();

        //测试多个数据源是否有效
        Main user = mainMapper.getUserByNameTest("admin");

        List<OrderItem> list = new ArrayList<OrderItem>(items.size());
        int totalCount=0;
        //优惠券
        int totalCoupon=0;
        int totalCurrency=0;
        int originalCoupon = 0;
        int originalCurrency = 0;

        Iterator<CartItem> itemIterator = items.iterator();
        while(itemIterator.hasNext()){
            CartItem it = itemIterator.next();
            Integer key = it.getKey();
            Integer count = it.getCount();
            if(key==null||count==null){
                continue;

            }
            Item item = getItem(key,userName);
            if(item == null){
                //logger.error("查找商品失败key=" + key);
                result.setCode(2);
                continue;
            }
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(item, orderItem);
            orderItem.setBuyCount(count);
            totalCount += orderItem.getBuyCount();
            totalCoupon += 0 * count;
            totalCurrency +=orderItem.getPrice() * count;
            originalCoupon += orderItem.getPrice() * count;
            originalCurrency +=orderItem.getPrice() * count;

            //orderItem.setItemName("test ItemName");
            //orderItem.setItemCategory(new ItemCategory());
            //orderItem.getItemCategory().setName("test category name");

            list.add(orderItem);
        }
        Order order = new Order();
        order.setItemsList(list);
        order.setOriginalCoupon(originalCoupon);
        order.setOriginalCurrency(originalCurrency);
        order.setTotalCount(totalCount);
        order.setTotalCoupon(totalCoupon);
        order.setTotalCurrency(totalCurrency);

        return order;

    }
    private Item getItem(Integer key,String userName){
        Date dateCurrent = new Date();

        checkCacheItems(dateCurrent);
        return itemList.get(key);

    }
    private void checkCacheItems(Date date){
        if(itemList == null){
            synchronized (itemlock){
                if(itemList == null){
                    List<Item> listItem  = itemMapper.findAll();
                    HashMap<Integer, Item> ilist = new HashMap<Integer, Item>();
                    for(Item e:listItem){
                        if(null!=e){
                            ilist.put(e.getKey(),e);
                        }
                    }
                    itemList = ilist;
                }
            }
        }

    }
}
