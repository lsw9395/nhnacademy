package com.nhnacademy.springmvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final Set<String> excludeUrls = new HashSet<>();
    private void init(){
        if(excludeUrls.isEmpty()){
            excludeUrls.add("/login");
            excludeUrls.add("/login.do");
            excludeUrls.add("/logout.do");
            excludeUrls.add("/loginForm.do");
            excludeUrls.add("/resources/");
            excludeUrls.add("/error");
            excludeUrls.add("/favicon.ico");
        }
    }
    private boolean urlCheck(String path){
        for (String excludeUrl : excludeUrls) {
            if(path.contains(excludeUrl)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        init();
        String requestUri = request.getRequestURI();
        log.error(request.getServletPath()+"됨1?");

        if(urlCheck(requestUri)){
            log.error("됨2?");
            HttpSession session = request.getSession(true);
            if(session.getAttribute("user")==null){
                log.error("됨3?");
                response.sendRedirect("/login/");
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
