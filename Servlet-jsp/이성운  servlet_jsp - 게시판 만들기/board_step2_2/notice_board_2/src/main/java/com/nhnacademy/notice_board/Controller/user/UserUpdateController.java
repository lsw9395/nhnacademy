package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import com.nhnacademy.notice_board.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UserUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        String id = req.getParameter("id");
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new RuntimeException("해당 유저가 없습니다.");
        }
        req.setAttribute("user",user);
        return "/user/userregister.jsp";
    }
}
