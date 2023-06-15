package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class UserUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");

        String id = req.getParameter("id");
        log.error("id:{}",req.getParameter("id"));
        if(Objects.isNull(id)){
            throw new RuntimeException("아이디를 입력해주세요.");
        }

        String password = req.getParameter("password");
        log.error("password:{}",req.getParameter("password"));
        if(Objects.isNull(password)){
            throw  new RuntimeException("비밀번호를 입력해주세요.");
        }
        String name = req.getParameter("name");
        if(Objects.isNull(name)){
            throw  new RuntimeException("이름을 입력해주세요.");
        }
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)){
            throw new RuntimeException("해당 유저가 없습니다.");
        }
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        User newUser = new User(id, password, name, user.getProfileFileName());
        userRepository.modify(newUser);

        return "redirect:/userlist.do";
    }
}
