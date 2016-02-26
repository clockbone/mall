package com.clockbone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qinjun on 2016/2/18.
 */
@Controller
public class IndexController {

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
}
