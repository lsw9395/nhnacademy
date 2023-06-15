package com.nhnacademy.notice_board.Controller;

import com.nhnacademy.notice_board.util.CookieUtils;
import com.nhnacademy.notice_board.util.CounterUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            //req.setAttribute("view","redirect:/loginForm.do");
            return "redirect:/loginForm.do";
        } else {
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
        if(!Objects.isNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        Cookie cookie2 =  CookieUtils.getCookie(req,"admin");
        if(!Objects.isNull(cookie2)){
            cookie2.setValue("");
            cookie2.setMaxAge(0);
            resp.addCookie(cookie2);
        }
        CounterUtils.loginDecreaseCounter(req.getServletContext());
        return "redirect:/loginForm.do";
    }
}
