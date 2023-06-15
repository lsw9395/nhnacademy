package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);//있으면 가져오고 없으면  null
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if(Objects.isNull(session)){
            //resp.sendRedirect("/loginForm.jsp");
            //req.setAttribute("view","redirect:/loginForm.do");
            return "redirect:/loginForm.do";
        } else {
            Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
//            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
//            rd.forward(req,resp);
            //req.setAttribute("view","login.jsp");
            return "/login.jsp";
        }
    }
}
