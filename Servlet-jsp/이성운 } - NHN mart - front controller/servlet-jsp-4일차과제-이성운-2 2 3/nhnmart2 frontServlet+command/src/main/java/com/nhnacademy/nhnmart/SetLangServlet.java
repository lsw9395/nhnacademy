package com.nhnacademy.nhnmart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(
        name = "setLangServlet",
        urlPatterns = "/change-lang"
)
public class SetLangServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie;
        if(req.getParameter("lang").equals("ko")){
            cookie = new Cookie("locale","ko");
        } else {
            cookie = new Cookie("locale","en");
        }
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.sendRedirect("/index.do");
    }
}
