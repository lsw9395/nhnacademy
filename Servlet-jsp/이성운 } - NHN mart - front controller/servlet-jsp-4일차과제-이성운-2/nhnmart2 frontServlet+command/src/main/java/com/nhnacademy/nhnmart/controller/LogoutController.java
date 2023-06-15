package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session)){
            //resp.sendRedirect("/loginForm.jsp");
            req.setAttribute("view","redirect:/loginForm.do");
            return "redirect:/loginForm.do";
        } else {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req,"id");
        if(!Objects.isNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        //resp.sendRedirect("/loginForm");
        //req.setAttribute("view","redirect:/loginForm.do");
        return "redirect:/loginForm.do";
    }
}
