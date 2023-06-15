package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.object.Food;
import com.nhnacademy.nhnmart.object.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class InitController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = new FoodStand();
        ServletContext context = req.getServletContext();
        foodStand.add(new Food("양파",Integer.parseInt(context.getInitParameter("양파")),2));
        foodStand.add(new Food("계란", Integer.parseInt(context.getInitParameter("계란")),5));
        foodStand.add(new Food("파", Integer.parseInt(context.getInitParameter("파")),10));
        foodStand.add(new Food("사과", Integer.parseInt(context.getInitParameter("사과")),20));
        context.setAttribute("foodStand",foodStand);
        return "/init.jsp";
    }
}
