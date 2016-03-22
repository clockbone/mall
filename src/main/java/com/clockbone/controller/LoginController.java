package com.clockbone.controller;

import com.clockbone.constant.Constant;
import com.clockbone.domain.User;
import com.google.common.base.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qinjun on 2016/2/5.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/index")
    public String index1(HttpServletRequest request,HttpServletResponse response){
        //登录成功设置用户登录信息session
        if(!Strings.isNullOrEmpty(request.getParameter("username"))){
            User user = new User();
            user.setUserName("testUser");
            request.getSession().setAttribute(Constant.SESSION_KEY,user);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String check(HttpServletRequest request,HttpServletResponse response){
        //登录成功设置用户登录信息session
        if(!Strings.isNullOrEmpty(request.getParameter("username"))){
            User user = new User();
            user.setUserName("testUser");
            request.getSession().setAttribute(Constant.SESSION_KEY,user);
            return "index";
        }
        return "login";
    }

    @RequestMapping("/denied")
    public String denied(){
        return "denied";
    }

    /**
     * 一个测试 登录失效页，可以直接跳转到登录页，这里只是测试
     * @return
     */
    @RequestMapping("/logoff")
    public String index2(){
        return "test";
    }


}
