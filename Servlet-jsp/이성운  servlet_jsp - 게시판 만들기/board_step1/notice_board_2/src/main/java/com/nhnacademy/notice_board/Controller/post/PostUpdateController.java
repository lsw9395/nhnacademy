package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class PostUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postList");
        Long postId = Long.parseLong(req.getParameter("postid"));
        Post post = postRepository.getPost(postId);
        if(Objects.isNull(post)){
            throw new RuntimeException("해당 게시글이 없습니다.");
        }
        req.setAttribute("post",post);
        return "/post/postregister.jsp";
    }
}
