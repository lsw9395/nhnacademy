package com.nhnacademy.springmvc.postlist.controller.user;

import com.nhnacademy.springmvc.postlist.domain.Page;
import com.nhnacademy.springmvc.postlist.exception.NotFoundUser;
import com.nhnacademy.springmvc.postlist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserListController {
    private final UserService userService;
    @GetMapping("/user_list")
    public String view(@RequestParam(value = "page") String page, Model model, HttpServletRequest req) throws NotFoundUser {
        log.info(page);
        int pageN = Integer.parseInt(page);

        Page page2 = userService.getPagedPosts(pageN,10);
        if(pageN>page2.getTotalPageCount()){
            throw new NotFoundUser(page);
        }
        model.addAttribute("pages",page2);
        model.addAttribute("visitorCounter",req.getServletContext().getAttribute("visitorCounter"));
        return "user/userlist";
    }
}
