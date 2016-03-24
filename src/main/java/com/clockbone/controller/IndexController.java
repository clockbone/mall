package com.clockbone.controller;

import com.clockbone.service.UserService;
import com.clockpone.domain.UserNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by qinjun on 2016/2/18.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/common", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("common");

        return mv;

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index1(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("index");

        return mv;

    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userindex(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("userlist");
        List<UserNew> list = userService.getAll();
        mv.addObject("userList", list);
        return mv;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request,UserNew userNew){
        ModelAndView mv = new ModelAndView("userlist");
        List<UserNew> list = userService.getAll();
        mv.addObject("userList", list);
        System.out.println("parameters="+userNew);
        int back = userService.add(userNew);
        System.out.println("back="+back);
        return mv;

    }




}
