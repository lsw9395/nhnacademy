package com.nhnacademy.springmvc.postlist.controller.login;

import com.nhnacademy.springmvc.postlist.domain.LoginRequest;
import com.nhnacademy.springmvc.postlist.domain.User;


import com.nhnacademy.springmvc.postlist.service.DbUserService;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login")
public class LoginController {
    private final DbUserService userService;
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
        } else {
            for(User user: userService.getUsers()){
                log.info(user.getId(),user.getPassword());
                if(user.getId().equals(loginRequest.getUserId())&&user.getPassword().equals(loginRequest.getUserPassword())){
                    HttpSession httpSession = request.getSession(true);//있으면 가져오고 없으면 생성함
                    httpSession.setAttribute("user",user);
                    return "login/login";
                }
            }
        }
        redirect.addFlashAttribute("message","로그인 실패");
        return "redirect:/login/";
    }
}
