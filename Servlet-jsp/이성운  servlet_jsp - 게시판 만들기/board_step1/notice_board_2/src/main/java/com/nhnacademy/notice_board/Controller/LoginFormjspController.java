package com.nhnacademy.notice_board.Controller;

import com.nhnacademy.notice_board.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormjspController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/loginForm.jsp";
    }
}
