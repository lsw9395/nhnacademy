package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.object.FoodStand;
import com.nhnacademy.nhnmart.util.CookieUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class FoodListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
        FoodStand foodStand = (FoodStand) context.getAttribute("foodStand");
        if(Objects.isNull(foodStand)){
            throw new RuntimeException("재고가 없습니다.");
        }
        String locale = CookieUtils.getCookie(req,"locale").getValue();
//        req.setAttribute("locale",locale);
        req.setAttribute("foods",foodStand.getFoods());
//        RequestDispatcher rd = req.getRequestDispatcher("foodList.jsp");
//        rd.forward(req,resp);
        //req.setAttribute("view","foodList.jsp");
        return "foodList.jsp";
    }
}
