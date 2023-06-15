package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.*;
import com.nhnacademy.notice_board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PostListController implements Command {
    private PostRepository postRepository;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postRepository = (PostRepository) req.getServletContext().getAttribute("postList");
        List<Post> postList = postRepository.getPosts();
        for(Post post : postList){
            log.info(post.getTitle());
        }
        int pagenum=0;
        if(Objects.isNull(req.getParameter("page"))){
            log.info("asdasdaf");
            pagenum = 1;
        } else {
            log.info("asdasddsdaf");
            pagenum = Integer.parseInt(req.getParameter("page"));
        }
        log.error("pagnum:{}",pagenum);
        Page page = postRepository.getPagedPosts(pagenum,3);

        req.setAttribute("pages",page);
        return "/post/postlist2.jsp";
    }
}