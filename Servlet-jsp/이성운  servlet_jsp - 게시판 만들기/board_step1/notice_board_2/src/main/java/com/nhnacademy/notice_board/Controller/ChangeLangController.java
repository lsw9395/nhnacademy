package com.nhnacademy.notice_board.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLangController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cookie cookie;
        if(req.getParameter("lang").equals("ko")){
            cookie = new Cookie("locale","ko");
        } else {
            cookie = new Cookie("locale","en");
        }
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        res.addCookie(cookie);
        return "redirect:/";
    }
}
