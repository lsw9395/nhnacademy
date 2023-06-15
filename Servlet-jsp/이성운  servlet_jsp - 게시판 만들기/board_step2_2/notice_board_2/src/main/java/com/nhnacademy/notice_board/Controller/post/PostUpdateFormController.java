package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class PostUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postList");

        String title = req.getParameter("title");
        log.error("title:{}",req.getParameter("title"));
        if(Objects.isNull(title)){
            throw new RuntimeException("제목을 입력하세요.");
        }

        String content = req.getParameter("content");
        log.error("content:{}",req.getParameter("content"));
        if(Objects.isNull(content)){
            throw  new RuntimeException("내용을 입력해주세요.");
        }
        Long postId = Long.parseLong(req.getParameter("id"));
        Post post = postRepository.getPost(postId);
        if(Objects.isNull(post)){
            throw new RuntimeException("해당 게시글이 없습니다.");
        }
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        Post newPost = new Post(post.getId(),title,content,post.getWriterUserId(),post.getWriteTime(), post.getViewCount());
        postRepository.modify(newPost);

        return "redirect:/postlist.do";
    }
}
