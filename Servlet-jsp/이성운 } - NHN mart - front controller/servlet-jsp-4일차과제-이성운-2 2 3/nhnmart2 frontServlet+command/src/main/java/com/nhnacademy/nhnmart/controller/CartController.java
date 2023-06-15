package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.object.Basket;
import com.nhnacademy.nhnmart.object.Food;
import com.nhnacademy.nhnmart.object.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CartController implements Command{
    public static Basket basket = new Basket();
    private final Set<String> excludes = new HashSet<>();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = (FoodStand) req.getServletContext().getAttribute("foodStand");
        String[] names = req.getParameterValues("name");
        String[] quantities = req.getParameterValues("quantity");
        String[] prices = req.getParameterValues("price");
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
                if(excludes.contains(food.getName())){
                    for(Food food3: basket.getFoods()){
                        if(food3.getName().equals(food.getName())){
                            int quantity2 = food3.getQuantity() + quantitiess[i];
                            food3.setQuantity(quantity2);
                        }
                    }
                } else {
                    basket.add(new Food(food.getName(), food.getPrice(), quantitiess[i]));
                    excludes.add(food.getName());
                }
            } else if(food.getQuantity()==0){
                log.error("Error:{}",food.getName()+"의 재고가 없습니다.");
            }
            i++;
        }
        HttpSession session = req.getSession();
        session.setAttribute("basket",basket);
        req.getServletContext().setAttribute("foodStand",foodStand);
//        RequestDispatcher rd = req.getRequestDispatcher("goCart.jsp");
//        rd.forward(req,resp);
//        req.setAttribute("view","/goCart.jsp");
        return "/goCart.jsp";
    }
}
