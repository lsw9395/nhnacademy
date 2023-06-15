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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class UserViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        log.info(req.getParameter("id"));

        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            throw new RuntimeException("id가 비어있음");
        }
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new RuntimeException("해당 "+id+"를 가진 유저가 없습니다.");
        }
        req.setAttribute("user",user);
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        String userId = session.getAttribute("id").toString();
        if(user.getId().equals(userId)){
            req.setAttribute("userid","true");
        }
        Cookie cookie = CookieUtils.getCookie(req,"admin");
        if(Objects.nonNull(cookie)){
            req.setAttribute("admin","true");
        }
        return "/user/userview.jsp";
    }
}
