package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

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
public class FoodListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        FoodStand foodStand = (FoodStand) context.getAttribute("foodStand");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'/>");
            out.println("<title>식품매대</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>상품목록</h1>");
            out.println("<form action='/cart' method='post'>");
            out.println("<ul>");
                for(Food food: foodStand.getFoods()){
                    out.println("<li>"+food.getName()+"("+food.getPrice()+"원): "+"수량: "+food.getQuantity()+"개"
                            + "<input type='hidden' id='name' name='name' value='"+food.getName()+"'>"
                            + "<input type='hidden' id='price' name='price' value='"+food.getPrice()+"'>"
                            + "<input type='number' id='quantity' name='quantity' value='0' min='0' max='"+food.getQuantity()+"'/>");
                }
            out.println("</ul>");
            out.println("<button type='submit'>장바구니 담기</button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");


        } catch (Exception e) {
            log.error("error:{}",e.getMessage(),e);
        }
    }
}
