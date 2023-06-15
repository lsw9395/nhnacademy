package com.nhnacademy.springmvc.postlist.filter;

import com.nhnacademy.springmvc.postlist.config.PropertiesConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
@Slf4j
@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = "/*"
)
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {
    private Set<String> excludeUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        PropertiesConfig propertiesConfig = (PropertiesConfig) context.getBean("propertiesConfig");
        excludeUrls = propertiesConfig.getUrls();
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        log.error(((HttpServletRequest) servletRequest).getServletPath()+1);

        // excludeUrls에 포함되지 않는다면 ..
        if(urlCheck(requestUri)){
            log.error(((HttpServletRequest) servletRequest).getServletPath()+2);
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
            if(Objects.isNull(session)||Objects.isNull(session.getAttribute("user"))&&Objects.isNull(session.getAttribute("admin"))){
                log.error(((HttpServletRequest) servletRequest).getServletPath()+3);
                ((HttpServletResponse) servletResponse).sendRedirect("/login/");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
