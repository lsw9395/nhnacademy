package com.nhnacademy.notice_board.Controller;


import com.nhnacademy.notice_board.util.CookieUtils;
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
        if(Objects.isNull(session.getAttribute("id"))){
            return "redirect:/loginForm.do";
        } else {
            Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
            if(Objects.nonNull(CookieUtils.getCookie(req,"admin"))){
                req.setAttribute("admin","admin");
            }
            return "/login.jsp";
        }
    }
}
