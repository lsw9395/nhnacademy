package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class PostRegisterFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        String writerUserId = session.getAttribute("id").toString();
        log.error("writerUserId: {}",writerUserId);
        req.setAttribute("writerUserId",writerUserId);
        if(!Objects.isNull(CookieUtils.getCookie(req,"locale"))){
            String locale = CookieUtils.getCookie(req,"locale").getValue();
            req.setAttribute("locale",locale);
        }
        return "/post/postregister.jsp";
    }
}
