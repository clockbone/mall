package com.clockbone.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by qinjun on 2016/2/16.
 */
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        PrintWriter out = servletResponse.getWriter();
        out.write("1");
        out.close();
    }

    @Override
    public void destroy() {

    }
}
