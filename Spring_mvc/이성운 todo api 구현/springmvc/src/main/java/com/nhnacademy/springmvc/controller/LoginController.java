package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.LoginRequest;
import com.nhnacademy.springmvc.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    private final User user;

    public LoginController(User user){
        this.user = user;
    }
    @GetMapping({"/",""})
    public String login(@CookieValue(value = "SESSION",required = false) String session, Model model){
        log.info("message:{}", model.getAttribute("message"));
        model.addAttribute("loginRequest",new LoginRequest());
        return "login/loginForm";
    }

    @PostMapping({"/",""})
    public String doLogin(@Validated @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                          HttpServletRequest request,
                          HttpServletResponse response, RedirectAttributes redirect){
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        if(user.getUserId().equals(loginRequest.getUserId())&&user.getUserPassword().equals(loginRequest.getUserPassword())){
            HttpSession session = request.getSession(true);

            Cookie cookie = new Cookie("SESSION",session.getId());
            response.addCookie(cookie);
            session.setAttribute("user",user);
            redirect.addFlashAttribute("user",user);
            return "redirect:/student/list.do";
        } else {
            redirect.addFlashAttribute("message","로그인 실패");
            return "redirect:/login/";
        }
    }

}
