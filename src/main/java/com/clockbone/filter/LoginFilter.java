package com.clockbone.filter;

import com.clockbone.constant.Constant;
import com.clockbone.domain.User;
import com.google.common.base.Strings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by qinjun on 2016/2/5.
 */
public class LoginFilter implements Filter {

    private String sessionKey = null;
    private String redirectURL = null;
    private List<String> notCheckURLList = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       /* Enumeration<String> e =  filterConfig.getInitParameterNames();
        while (e.hasMoreElements()){
            String key = e.nextElement();
            String value = filterConfig.getInitParameter(key);
            //todo something...
            System.out.println(key + value);
        }*/

        sessionKey = filterConfig.getInitParameter("sessionKey");//LOGIN_NAME
        redirectURL = filterConfig.getInitParameter("redirectURL");
        String notCheckList = filterConfig.getInitParameter("notCheckURLList");
        if(!Strings.isNullOrEmpty(notCheckList)){
            StringTokenizer st = new StringTokenizer(notCheckList,";");
            notCheckURLList.clear();
            while (st.hasMoreTokens()){
                notCheckURLList.add(st.nextToken());
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();

        //获取请求的地址
        String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());

        String uri=request.getRequestURI();

        //如果是登录请求不拦截
        if("/user/login".equals(url)||"/favicon.ico".equals(url)){
            filterChain.doFilter(request, response);
            return;
        }

        //是否是图片css js
        if(uri.endsWith(".js")
                ||uri.endsWith(".css")
                ||uri.endsWith(".jpg")
                ){//是否是图片css js
            filterChain.doFilter(request, response);
            return;
        }
        //sessionKey=LOGIN_USER，存储的登录信息主键
        if(Strings.isNullOrEmpty(sessionKey)){
           throw  new NullPointerException("sessionKey is null!");
        }
        Object o = request.getSession().getAttribute(sessionKey);
        if(null == o){
            //用户登录信息为空，跳转到登录页
            response.sendRedirect("/user/login");
            return;
        }
        if(checkRequestURIIntNotFilterList(request)){
            filterChain.doFilter(request, response);
            return;
        }

        //UserDetails userDetail = (UserDetails)SpringSecurityUtils.getCurrentUser();
        //request.get

    /*    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(userDetails == null ){

        }*/

        //找不到用户登录信息返回
        String userName = (String)request.getSession().getAttribute("userName");
        //todo something...
        /*
        if(null == userName||"".equals(userName)){
            response.sendRedirect("login");
            return;
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
        */
        filterChain.doFilter(servletRequest,servletResponse);



    }

    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
        return notCheckURLList.contains(url);
    }

    @Override
    public void destroy() {

    }
}
