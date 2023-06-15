package com.nhnacademy.springmvc.postlist.controller.post;

import com.nhnacademy.springmvc.postlist.domain.Page;

import com.nhnacademy.springmvc.postlist.exception.NotFoundPost;
import com.nhnacademy.springmvc.postlist.service.DbPostService;
import com.nhnacademy.springmvc.postlist.service.PostService;
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
public class PostListController {
    //private final PostService postService;
    private final DbPostService postService;
    @GetMapping("/post_list")
    public String view(@RequestParam(value = "page",required = true) String page, Model model, HttpServletRequest req) throws NotFoundPost {
        log.info(page);
        int pageN = Integer.parseInt(page);
        Page page2 = postService.getPagedPosts(pageN,10);
        if(pageN>page2.getTotalPageCount()){
            throw new NotFoundPost(pageN);
        }
        int startPage =(int) ((pageN-1)/5)*5 +1;
        int endPage = (int) Math.min(startPage+4,page2.getTotalCount());
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("pages",page2);
        model.addAttribute("visitorCounter",req.getServletContext().getAttribute("visitorCounter"));
        return "post/postlist";
    }
}
