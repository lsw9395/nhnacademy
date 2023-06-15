package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
public class LogoutController {

    @PostMapping("/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        if(session.getAttribute("user")==null){
            return "redirect:/";
        } else {
            session.invalidate();
        }
        Cookie cookie = CookieUtils.getCookie(req,"SESSION");
        if(!Objects.isNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        return "redirect:/";
    }
}
