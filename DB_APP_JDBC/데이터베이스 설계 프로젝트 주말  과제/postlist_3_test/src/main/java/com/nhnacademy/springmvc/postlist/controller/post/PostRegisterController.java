package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.domain.PostRegisterRequest;
import com.nhnacademy.springmvc.postlist.domain.User;
import com.nhnacademy.springmvc.postlist.service.DbPostService;
import com.nhnacademy.springmvc.postlist.service.PostService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostRegisterController {
    private final DbPostService postService;

    @GetMapping("/post_register")
    public String registerForm(Model model){
        model.addAttribute("postRegisterRequest",new PostRegisterRequest());
        return "post/postregister";
    }
    @PostMapping(value = "/post_register")
    public String getPost(@Validated @ModelAttribute PostRegisterRequest postRegisterRequest, BindingResult bindingResult
                            ,HttpServletRequest req){
        log.info(postRegisterRequest.getTitle());
        log.info(postRegisterRequest.getContent());
        if(bindingResult.hasErrors()){
            log.info("binding error");
            return "post/postregister";
        }
        Post post = new Post();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(Objects.isNull(user)){
            user = (User) session.getAttribute("admin");
        }
        post.setTitle(postRegisterRequest.getTitle());
        post.setContent(postRegisterRequest.getContent());
        post.setWriterUserId(user.getId());
        postService.register(post);
        return "redirect:/post_list?page=1";
    }
}
