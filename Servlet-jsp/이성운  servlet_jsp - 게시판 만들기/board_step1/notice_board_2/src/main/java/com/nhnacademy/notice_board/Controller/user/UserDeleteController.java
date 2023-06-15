package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.PostRepository;
import com.nhnacademy.notice_board.objects.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        String id = req.getParameter("id");
        userRepository.remove(id);
        return "redirect/userlist.do";
    }
}
