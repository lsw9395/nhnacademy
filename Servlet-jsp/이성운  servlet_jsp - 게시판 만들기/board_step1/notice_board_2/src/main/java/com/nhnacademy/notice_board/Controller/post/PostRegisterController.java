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
public class PostRegisterController implements Command {
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
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        String writerUserId = session.getAttribute("id").toString();
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriterUserId(writerUserId);
        long postId= postRepository.register(post);
        post.setId(postId);

        return "redirect:/postview.do?postid="+postId;
    }
}
