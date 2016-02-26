package com.clockbone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qinjun on 2016/2/5.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/login")
    public String index1(){
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
