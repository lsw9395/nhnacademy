package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.exception.NotFoundUserId;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping("/user_view")
    public String view(@RequestParam(value = "id",required = true) String id, Model model) throws NotFoundUserId {
        User user = userService.getUser(id);
        if(Objects.isNull(user)){
            throw new NotFoundUserId(id);
        }
        model.addAttribute("user",user);
        return "user/userview";
    }
}
