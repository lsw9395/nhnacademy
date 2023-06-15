package com.nhnacademy.springboot.postlist.board.controller.login;

import com.nhnacademy.springboot.postlist.board.domain.LoginRequest;
import com.nhnacademy.springboot.postlist.board.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login")
public class LoginController {

    private final User admin;
    @GetMapping({"/",""})
    public String login(Model model,HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user")==null&&session.getAttribute("admin")==null){
            model.addAttribute("loginRequest",new LoginRequest());
            return "login/loginForm";
        }
        return "login/login";
    }
    @PostMapping({"/",""})
    public String doLogin(@Validated @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                          HttpServletRequest request,
                          HttpServletResponse response, RedirectAttributes redirect){
        log.info(loginRequest.getUserId());
        log.info(loginRequest.getUserPassword());
        if(bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        if(admin.getId().equals(loginRequest.getUserId())&&admin.getPassword().equals(loginRequest.getUserPassword())){
            HttpSession session = request.getSession(true);
            session.setAttribute("admin",admin);
            return "login/login";
        }
        redirect.addFlashAttribute("message","로그인 실패");
        return "redirect:/login/";
    }
}
