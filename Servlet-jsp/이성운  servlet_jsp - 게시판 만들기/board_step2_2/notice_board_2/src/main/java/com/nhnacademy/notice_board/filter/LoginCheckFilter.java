package com.nhnacademy.notice_board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Slf4j
@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclude-urls",value = "/login.do\n" + "/logout.do\n" + "/loginForm.do\n" +"/userregister.do\n"+"/user/userregister.jsp\n"
                +"/change_lang.do\n"+"/error.json\n"+"/error.do\n")
        }
)
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.error("exclude-urls:{}",urls);
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        log.error(((HttpServletRequest) servletRequest).getServletPath()+"됨1?");
        // excludeUrls에 포함되지 않는다면 ..
        if(!excludeUrls.contains(requestUri)){
            log.error("됨2?");
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
            if(session.getAttribute("id")==null){
                log.error("됨3?");
                ((HttpServletResponse) servletResponse).sendRedirect("/loginForm.do");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
