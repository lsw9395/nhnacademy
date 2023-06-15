package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "redirect:/user/userregister.jsp";
    }
}
