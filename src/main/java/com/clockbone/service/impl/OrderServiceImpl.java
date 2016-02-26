package com.clockbone.service.impl;

import com.clockbone.domain.Item;
import com.clockbone.domain.Order;
import com.clockbone.domain.OrderItem;
import com.clockbone.mapper.ItemMapper;
import com.clockbone.service.OrderService;
import com.clockbone.vo.CartItem;
import com.clockbone.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.beancontext.BeanContext;
import java.util.*;

/**
 * Created by qinjun on 2016/2/24.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private ItemMapper itemMapper;

    private static final Object itemlock = new Object();

    private static volatile HashMap<String, Item> itemList = null;

    @Override
    public Order createOrder(List<CartItem> items, String payType, Result result) {
        return createOrderProcess(items,payType,null,null,result);
    }

    private Order createOrderProcess(List<CartItem> items,String payType,String userName,String popu,Result result){

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
            String key = it.getKey();
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
            totalCoupon += orderItem.getCurrentCoupon() * count;
            totalCurrency += orderItem.getCurrentCurrency() * count;
            originalCoupon += orderItem.getCoupon() * count;
            originalCurrency += orderItem.getCurrency() * count;
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
    private Item getItem(String key,String userName){
        Date dateCurrent = new Date();

        checkCacheItems(dateCurrent);
        return itemList.get(key);

    }
    private void checkCacheItems(Date date){
        if(itemList == null){
            synchronized (itemlock){
                if(itemList == null){
                    List<Item> listItem  = itemMapper.findAll();
                    HashMap<String, Item> ilist = new HashMap<String, Item>();
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
