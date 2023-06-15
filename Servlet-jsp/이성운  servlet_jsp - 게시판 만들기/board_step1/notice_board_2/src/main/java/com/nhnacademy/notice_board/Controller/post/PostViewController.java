package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class PostViewController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postList");
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userList");
        log.info(req.getParameter("postid"));
        Long postId = Long.parseLong(req.getParameter("postid"));

        if(Objects.isNull(postId)){
            throw new RuntimeException("id가 비어있음");
        }
        Post post = postRepository.getPost(postId);
        if(Objects.isNull(post)){
            throw new RuntimeException("해당 "+postId+"를 가진 게시글이 없습니다.");
        }
        req.setAttribute("post",post);
        HttpSession session = req.getSession(true);
        if(session.getAttribute("id")==null){
            return "redirect:/loginForm.do";
        }
        String writerUserId = session.getAttribute("id").toString();
        if(post.getWriterUserId().equals(writerUserId)){
            req.setAttribute("writer","true");
        }
        User user = userRepository.getUser(writerUserId);
        req.setAttribute("user",user);
        post.setViewCount(post.getViewCount()+1);
        return "/post/postview.jsp";
    }
}
