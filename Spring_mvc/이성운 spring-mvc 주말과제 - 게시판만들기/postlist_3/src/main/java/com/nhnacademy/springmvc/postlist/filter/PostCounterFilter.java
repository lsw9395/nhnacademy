package com.nhnacademy.springmvc.postlist.filter;


import com.nhnacademy.springmvc.postlist.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebFilter(
        filterName = "postCounterFilter",
        urlPatterns = {"/*"}
)
public class PostCounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String param = req.getParameter("id");
        log.info(param);
        log.info(req.getContextPath());
        log.info(req.getRequestURI());
        log.info(req.getRequestURL().toString());
        if(Objects.isNull(CookieUtils.getCookie(req,"check_counter"))){
            Cookie cookie = new Cookie("check_counter",null);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
            ((HttpServletResponse) response).addCookie(cookie);
        } else if(param!=null){
            log.info("3");
            Cookie cookie = CookieUtils.getCookie(req,"check_counter");
            String cookieValue = cookie.getValue();
            if(!cookieValue.contains(param)){
                log.info("4");
                String newCookieValue = cookieValue+"/"+param+"/";
                Cookie cookie1 = new Cookie("check_counter",newCookieValue);
                cookie1.setSecure(true);
                cookie1.setHttpOnly(true);
                cookie1.setMaxAge(60*60*24);
                cookie.setPath("/");
                ((HttpServletResponse) response).addCookie(cookie1);
            }
        }
        chain.doFilter(request,response);
    }
}
