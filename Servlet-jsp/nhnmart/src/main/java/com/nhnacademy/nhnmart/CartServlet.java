package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class CartServlet extends HttpServlet {
    public static Basket basket = new Basket();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        FoodStand foodStand = (FoodStand) getServletContext().getAttribute("foodStand");
        String[] names = req.getParameterValues("name");
        String[] quantities = req.getParameterValues("quantity");
        String[] prices = req.getParameterValues("price");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        int[] quantitiess = new int[4];
        int[] pricess = new int[4];
        for(int i = 0 ; i < quantities.length;i++){
            quantitiess[i] =Integer.parseInt(quantities[i]);
        }
        for(int i = 0 ; i < prices.length;i++){
            pricess[i] =Integer.parseInt(prices[i]);
        }
        int i = 0;
        for(Food food:foodStand.getFoods()){
            if(quantitiess[i]!=0 && food.getQuantity()!=0){
                int quantity = food.getQuantity()-quantitiess[i];
                food.setQuantity(quantity);
                if(basket.getFoods().contains(food)){
                    int index = basket.getFoods().indexOf(food.getName());
                    int quantity2 = basket.getFoods().get(index).getQuantity() + quantitiess[i];
                    basket.getFoods().get(index).setQuantity(quantity2);
                } else {
                    basket.add(new Food(food.getName(), food.getPrice(), quantitiess[i]));
                }

            }
            i++;
        }
        HttpSession session = req.getSession();
        session.setAttribute("basket",basket);
        getServletContext().setAttribute("foodStand",foodStand);
        try(PrintWriter out = resp.getWriter()){
            out.println("<h1>");
            out.println("<a href='/cart'>장바구니</a>");
            out.println("</h1>");
        } catch (Exception e){
            log.error("error:{}",e.getMessage(),e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if(Objects.isNull(session)) {
            resp.sendRedirect("/foods");
        } else {
            Basket basket = (Basket) session.getAttribute("basket");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'/>");
                out.println("<title>장바구니</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>장바구니 목록</h1>");
                out.println("<table border='1'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>제품 이름</th>");
                out.println("<th>제품 가격</th>");
                out.println("<th>제품 갯수</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                int sum = 0;
                for(Food food:basket.getFoods()){
                    out.println("<tr>");
                    out.println("<td>"+food.getName()+"</td>");
                    out.println("<td>"+food.getPrice()+"</td>");
                    out.println("<td>"+food.getQuantity()+"</td>");
                    out.println("</tr>");
                    sum += food.getQuantity()*food.getPrice();
                }
                out.println("</tbody>");
                out.println("</table>");
                out.println("<h1>");
                out.println(sum+"원");
                out.println("</h1>");
            } catch (Exception e) {
                log.error("error:{}", e.getMessage(), e);
            }
        }
    }
}
