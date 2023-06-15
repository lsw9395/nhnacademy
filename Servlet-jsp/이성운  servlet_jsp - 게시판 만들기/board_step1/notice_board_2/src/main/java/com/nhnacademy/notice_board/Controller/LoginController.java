package com.nhnacademy.notice_board.Controller;


import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import com.nhnacademy.notice_board.util.CounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
public class LoginController implements Command{
    private String adminUserId = "admin";
    private String adminPassword = "12345";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        List<User> users = userRepository.getUsers();
        String Id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");
        if(adminUserId.equals(Id) && adminPassword.equals(pwd)){
            Cookie cookie2 = new Cookie("admin","admin");
            cookie2.setMaxAge(-1);
            cookie2.setPath("/");
            resp.addCookie(cookie2);
            HttpSession httpSession = req.getSession(true);//있으면 가져오고 없으면 생성함
            httpSession.setAttribute("id",Id);
            CounterUtils.loginIncreaseCounter(req.getServletContext());
            return "redirect:/login.do";
        } else {
            for(User user:users){
                if(user.getId().equals(Id)&&user.getPassword().equals(pwd)){
                    HttpSession httpSession = req.getSession(true);//있으면 가져오고 없으면 생성함
                    httpSession.setAttribute("id",Id);
                    CounterUtils.loginIncreaseCounter(req.getServletContext());
                    return "redirect:/login.do";
                }
            }
        }
        return "redirect:/loginForm.do";
    }

}
