package com.t09.jibao.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login/checkAccount"}, filterName = "captchaFilter")
public class CaptchaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        System.out.println("filter:" + request.getRequestURL().toString());
        System.out.println(session);
        if(session != null) {
            Object image_id_object = session.getAttribute("image_id");
            System.out.println(image_id_object);
            if (image_id_object != null) {
                System.out.println("pass");
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
                servletResponse.getWriter().write("no captcha!!!");
        }
        else
            servletResponse.getWriter().write("no captcha!!!");
    }

    @Override
    public void destroy() {
        // Filter.super.destroy();
    }
}
