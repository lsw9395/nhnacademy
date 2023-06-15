package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        FoodStand foodStand = new FoodStand();
        ServletContext context = config.getServletContext();
        foodStand.add(new Food("양파",Integer.parseInt(context.getInitParameter("양파")),2));
        foodStand.add(new Food("계란", Integer.parseInt(context.getInitParameter("계란")),5));
        foodStand.add(new Food("파", Integer.parseInt(context.getInitParameter("파")),10));
        foodStand.add(new Food("사과", Integer.parseInt(context.getInitParameter("사과")),20));
        context.setAttribute("foodStand",foodStand);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>식품매대 준비</title>");
                    out.println("<meta charset='utf-8'/>");
                out.println("</head>");
                out.println("<body>");
                    out.println("<h1>");
                        out.println("<a href='/foods'>상품목록</a>");
                    out.println("</h1>");
                out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            log.error("error:{}",e.getMessage(),e);
        }

    }
}
