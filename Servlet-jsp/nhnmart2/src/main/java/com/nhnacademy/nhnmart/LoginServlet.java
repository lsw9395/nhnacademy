package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login",
        initParams = {
                @WebInitParam(name = "id", value = "lee"),
                @WebInitParam(name = "pwd", value = "1234")
        }
)
public class LoginServlet extends HttpServlet {

    private String initUserId;

    private String initUserPassword;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initUserId = config.getInitParameter("id");
        initUserPassword = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession(false);//있으면 가져오고 없으면  null
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if(Objects.isNull(session)){
            resp.sendRedirect("/loginForm.html");
        } else {
            Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");
        if(initUserId.equals(Id) && initUserPassword.equals(pwd)){
            Cookie cookie2 = new Cookie("locale","ko");
            cookie2.setMaxAge(-1);
            cookie2.setPath("/");
            resp.addCookie(cookie2);
            HttpSession httpSession = req.getSession(true);//있으면 가져오고 없으면 생성함
            httpSession.setAttribute("id",Id);
            resp.sendRedirect("/login");
        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
            resp.sendRedirect("./loginForm.html");
        }
    }
}
