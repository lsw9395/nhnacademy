package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.object.Food;
import com.nhnacademy.nhnmart.object.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(
        name = "foodListServlet",
        urlPatterns = "/foods"
)
public class FoodListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        FoodStand foodStand = (FoodStand) context.getAttribute("foodStand");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("foods",foodStand.getFoods());
        RequestDispatcher rd = req.getRequestDispatcher("foodList.jsp");
        rd.forward(req,resp);
    }
}
