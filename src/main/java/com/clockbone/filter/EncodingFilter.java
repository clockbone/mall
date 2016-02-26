package com.clockbone.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by qinjun on 2016/2/16.
 */
public class EncodingFilter extends HttpServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        System.out.println("这是一个测试过滤器");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
