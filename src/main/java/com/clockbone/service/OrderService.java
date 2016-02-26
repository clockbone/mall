package com.clockbone.service;

import com.clockbone.domain.Order;
import com.clockbone.vo.CartItem;
import com.clockbone.vo.Result;

import java.util.List;

/**
 * Created by qinjun on 2016/2/24.
 */
public interface OrderService {
    public Order createOrder(List<CartItem> items,String payType,Result result) ;
}
