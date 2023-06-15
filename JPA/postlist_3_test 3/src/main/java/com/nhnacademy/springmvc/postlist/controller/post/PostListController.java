package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.PostDto;
import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.exception.NotFoundPost;
import com.nhnacademy.springmvc.postlist.repository.JpaPostRepository;
import com.nhnacademy.springmvc.postlist.service.DbPostService;
import com.nhnacademy.springmvc.postlist.service.PostService;
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
public class PostListController {
    //private final PostService postService;
    private final JpaPostRepository postService;
    @GetMapping("/post_list")
    public String view(Pageable pageable, Model model,HttpServletRequest req) throws NotFoundPost {
        Page<PostDto> page = postService.getAllBy(pageable);
        List<PostDto> postEntityList = page.getContent();
        int pageN = page.getNumber();
        int startPage =(int) ((pageN-1)/5)*5 +1;
        int endPage = (int) Math.min(startPage+4,page.getTotalPages());
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("datas",postEntityList);
        model.addAttribute("pages",page);
        model.addAttribute("visitorCounter",req.getServletContext().getAttribute("visitorCounter"));
        return "post/postlist";
    }
}
