package com.clockbone.controller;

import com.clockbone.domain.Order;
import com.clockbone.domain.OrderItem;
import com.clockbone.service.OrderService;
import com.clockbone.vo.CartItem;
import com.clockbone.vo.Result;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinjun on 2016/2/24.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private static final String VIEW_CART="cart";
    private static final String COOKIE_VALUE="TEST_CART";

    private static final String ITEM_SEPRARTOR=":";
    private static final String ITEM_COUNT_SEPRARTOR="_";

    private static final String MODEL_NAME = "model";

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@CookieValue(value=COOKIE_VALUE,required = false)String myCarts,
                       Model model,HttpServletRequest request,HttpServletResponse response){

        myCarts = Strings.nullToEmpty(myCarts);

        Cookie[] cookies = request.getCookies();

        //从cookies中获取购买的商品信息
        List<CartItem> items = getItemHashMap(myCarts);
        String errorMessage=null;

        Result result = new Result();
        Order order = orderService.createOrder(items,"2",result);
        if(result.isHasError()){
            errorMessage = "创建订单失败：" + result.getCode();
        }
        model.addAttribute(MODEL_NAME,order);
        model.addAttribute("error",errorMessage);
        //将定单写到cookie
        addOrderToCookie(response,order);
        return VIEW_CART;

    }

    private void addOrderToCookie(HttpServletResponse response,Order order){
        if(null==order){
            addToCookie(response,"");
        }
        StringBuilder sb = new StringBuilder();
        //构造 cookie信息
        for(OrderItem o:order.getItemsList()){
            sb.append(o.getKey()).append(ITEM_COUNT_SEPRARTOR).append(o.getBuyCount()).append(ITEM_SEPRARTOR);
        }
        //将构造的cookie写
        addToCookie(response, sb.toString());

    }

    private static final String CART_COOKIE="TEST_CART";
    private static final String COOKIES_DOMAIN=".test.clock.com";
    private static final String COOKIES_DOMAIN2=".clock.com";

    private void addToCookie(HttpServletResponse response,String values){

        //添加过滤后的 cookie信息
        Cookie cookies = new Cookie(CART_COOKIE,values);
        //设置路径，不设置默认是只在当前请求的url下有效，设置为/ ：表示在此工程下所有请求路经都在效
        cookies.setPath("/");
        //设置有效的cookie访问域名
        cookies.setDomain(COOKIES_DOMAIN);
        response.addCookie(cookies);

        //清除之前页面上添加的cookie信息
        Cookie cookieDel = new Cookie(CART_COOKIE, "");
        cookieDel.setPath("/");
        cookieDel.setDomain(COOKIES_DOMAIN2);
        //设置成失效
        cookieDel.setMaxAge(0);
        response.addCookie(cookieDel);





    }

    private List<CartItem> getItemHashMap(String myCarts){
        String[] items = myCarts.split(ITEM_SEPRARTOR);
        List<CartItem> list = new ArrayList<CartItem>();
        for(String it:items){
            int colonIndex = it.indexOf(ITEM_COUNT_SEPRARTOR);
            if(colonIndex!=-1){
                Integer key = Integer.parseInt(it.substring(0, colonIndex));
                String count = it.substring(colonIndex+1);
                if(null!=key&&!Strings.isNullOrEmpty(count)){
                    CartItem cartItem = new CartItem(key,Integer.parseInt(count));
                    list.add(cartItem);
                }
            }
        }
        return list;

    }
}
