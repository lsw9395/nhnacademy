package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import com.nhnacademy.notice_board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UserListController implements Command {
    private UserRepository userRepository;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        List<User> userList = userRepository.getUsers();
        for(User user : userList){
            log.info(user.getId());
        }
        if(Objects.nonNull(CookieUtils.getCookie(req,"admin"))){
            req.setAttribute("admin","admin");
        }
        req.setAttribute("counter",req.getServletContext().getAttribute("counter"));
        req.setAttribute("users",userList);
        req.setAttribute("login_counter",req.getServletContext().getAttribute("login_counter"));
        return "/user/userlist.jsp";
    }
}
