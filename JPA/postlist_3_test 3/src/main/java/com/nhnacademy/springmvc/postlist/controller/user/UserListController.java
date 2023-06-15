package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.UserDto;
import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import com.nhnacademy.springmvc.postlist.exception.NotFoundUser;
import com.nhnacademy.springmvc.postlist.repository.JpaUserRepository;
import com.nhnacademy.springmvc.postlist.service.DbUserService;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserListController {
    private final JpaUserRepository userService;
    @GetMapping("/user_list")
    public String view(Pageable pageable, Model model, HttpServletRequest req) throws NotFoundUser {
        Page<UserDto> page = userService.getAllBy(pageable);
        List<UserDto> userEntityList = page.getContent();
        int pageN = page.getNumber();
        int startPage = ((pageN-1)/5) *5 +1;
        int endPage =  Math.min(startPage+4,page.getTotalPages());
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("datas",userEntityList);
        model.addAttribute("pages",page);
        model.addAttribute("visitorCounter",req.getServletContext().getAttribute("visitorCounter"));
        return "user/userlist";
    }
}
