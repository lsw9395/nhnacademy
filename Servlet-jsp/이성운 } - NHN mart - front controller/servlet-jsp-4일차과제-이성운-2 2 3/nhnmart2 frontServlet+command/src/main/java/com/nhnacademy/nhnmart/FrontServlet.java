package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.controller.*;
import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        if(!Objects.isNull(CookieUtils.getCookie(req,"locale"))){
            String locale = CookieUtils.getCookie(req,"locale").getValue();
            req.setAttribute("locale",locale);
        }
        try {
            Command command = resolveCommand(req.getServletPath(),req.getMethod());
            String view = command.execute(req, resp);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length() + 1));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req,resp);
            }
        }catch (Exception ex){
            Command error = new ErrorController();
            String view2 = error.execute(req,resp);
            RequestDispatcher rd = req.getRequestDispatcher(view2);
            rd.include(req,resp);
        }

    }

    private Command resolveCommand(String servletPath, String method){
        Command command = null;
        if("/cart.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new CartController();
        }
        if("/cart.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new CartFormController();
        }
        if("/init.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new InitController();
        }
        if("/foods.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new FoodListController();
        }
        if("/index.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new IndexController();
        }
        if("/loginForm.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new LoginFormjspController();
        }
        if("/login.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
            command = new LoginController();
        }
        if("/login.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
            command = new LoginFormController();
        }
        if("/logout.do".equals(servletPath)&&"POST".equalsIgnoreCase(method)){
            command = new LogoutController();
        }
        return command;
    }

}