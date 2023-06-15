package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
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
            resp.sendRedirect("/login.html");
        } else {
            Cookie cookie = CookieUtils.getCookie(req,"JSESSIONID");
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1><a href='/index.html'>메인</a></h1>");
                out.println("<form method='post' action='/logout'>");
                out.println("<p><button type='submit'>로그아웃</button></p>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }catch (Exception e){
                log.error("login {}:",e.getMessage(),e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");
        if(initUserId.equals(Id) && initUserPassword.equals(pwd)){
            HttpSession httpSession = req.getSession(true);//있으면 가져오고 없으면 생성함
            httpSession.setAttribute("id",Id);
            resp.sendRedirect("/login");
        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
            resp.sendRedirect("./login.html");
        }
    }
}
