package com.t09.jibao.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * static resource filter
 * allow: visit goods image
 * not allow: visit others avatar
 */
@WebFilter(urlPatterns = {"/user/info/*", "/goods/*"}, filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if(session != null) {
            Object uid_object = session.getAttribute("uid");
            if (uid_object != null)
                filterChain.doFilter(servletRequest, servletResponse);
            else
                servletResponse.getWriter().write("no login!!!");
        }
        else
            servletResponse.getWriter().write("no login!!!");
    }

    @Override
    public void destroy() {
        // Filter.super.destroy();
    }
}
