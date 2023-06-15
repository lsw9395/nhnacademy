package com.nhnacademy.springmvc.postlist.filter;

import com.nhnacademy.springmvc.postlist.util.CookieUtils;
import com.nhnacademy.springmvc.postlist.util.CounterUtils;
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
        filterName = "visitorCounterFilter",
        urlPatterns = {"/*"}
)
public class VisitorCounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(Objects.isNull(CookieUtils.getCookie((HttpServletRequest) request,"visit_check"))){
            Cookie cookie = new Cookie("visit_check","test");
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            ((HttpServletResponse) response).addCookie(cookie);
            Long visitCounter = CounterUtils.visitIncreaseCounter(request.getServletContext());
            request.getServletContext().setAttribute("visitCounter",visitCounter);
            chain.doFilter(request,response);
        }
        log.error("사이트 방문한지 1시간 안 넘음");
        chain.doFilter(request,response);
    }
}
