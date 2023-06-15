package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.object.Basket;
import com.nhnacademy.nhnmart.object.Food;
import com.nhnacademy.nhnmart.object.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@WebServlet(
        name = "cartServlet",
        urlPatterns = "/cart"
)
public class CartServlet extends HttpServlet {
    public static Basket basket = new Basket();
    private final Set<String> excludes = new HashSet<>();
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
            }
            i++;
        }
        HttpSession session = req.getSession();
        session.setAttribute("basket",basket);
        getServletContext().setAttribute("foodStand",foodStand);
        RequestDispatcher rd = req.getRequestDispatcher("goCart.jsp");
        rd.forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        Basket basket2 = (Basket) session.getAttribute("basket");
        req.setAttribute("foods",basket2.getFoods());
        RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
        rd.forward(req,resp);
    }

}
