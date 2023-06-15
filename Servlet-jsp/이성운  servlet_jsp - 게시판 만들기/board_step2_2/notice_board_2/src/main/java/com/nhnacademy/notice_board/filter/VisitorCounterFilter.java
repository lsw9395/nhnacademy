package com.nhnacademy.notice_board.filter;

import com.nhnacademy.notice_board.util.CounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(
        filterName = "visitorCounterFilter",
        urlPatterns = {"/postview.do","/userview.do"}
)
public class VisitorCounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CounterUtils.increaseCounter(request.getServletContext());
        chain.doFilter(request,response);
        log.error("counter:{}",request.getServletContext().getAttribute("counter"));
    }
}
