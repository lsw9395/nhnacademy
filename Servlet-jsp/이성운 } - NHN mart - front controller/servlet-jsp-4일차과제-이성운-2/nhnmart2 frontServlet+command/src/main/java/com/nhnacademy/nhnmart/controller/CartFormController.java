package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.object.Basket;
import com.nhnacademy.nhnmart.object.Food;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class CartFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Basket basket2 = (Basket) session.getAttribute("basket");
        if(Objects.isNull(basket2)){
            basket2 = new Basket();
        }
        int sum = 0;

        for(Food food:basket2.getFoods()){
            sum+=food.getQuantity()*food.getPrice();
        }
//        String locale = CookieUtils.getCookie(req,"locale").getValue();
//        req.setAttribute("locale",locale);
        req.setAttribute("total_price",sum);
        req.setAttribute("foods",basket2.getFoods());
//        RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
//        rd.forward(req,resp);
        //req.setAttribute("view","/cart.jsp");
        return "/cart.jsp";
    }
}
