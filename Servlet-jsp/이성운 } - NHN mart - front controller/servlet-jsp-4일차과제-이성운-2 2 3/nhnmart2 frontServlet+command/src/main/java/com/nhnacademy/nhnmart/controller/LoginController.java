package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginController implements Command{
    private String initUserId = "lee";
    private String initUserPassword = "1234";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String Id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");
        if(initUserId.equals(Id) && initUserPassword.equals(pwd)){
            Cookie cookie2 = new Cookie("locale","ko");
            cookie2.setMaxAge(-1);
            cookie2.setPath("/");
            resp.addCookie(cookie2);
            HttpSession session = req.getSession(true);
            session.setAttribute("id",Id);
//            Cookie cookie = new Cookie("id","id");
//            cookie.setMaxAge(-1);
//            cookie.setPath("/");
//            resp.addCookie(cookie);
//            resp.sendRedirect("/login");
            //req.setAttribute("view","redirect:/login.do");
            return "redirect:/login.do";
        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
//            resp.sendRedirect("./loginForm.jsp");
//            String locale = CookieUtils.getCookie(req,"locale").getValue();
//            req.setAttribute("locale",locale);
            //req.setAttribute("view","redirect:/loginForm.do");
            return "redirect:/loginForm.do";
        }
    }

}
