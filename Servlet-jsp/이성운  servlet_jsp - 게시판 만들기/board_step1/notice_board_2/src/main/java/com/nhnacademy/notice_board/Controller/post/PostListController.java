package com.nhnacademy.notice_board.Controller.post;

import com.nhnacademy.notice_board.Controller.Command;
import com.nhnacademy.notice_board.objects.Post;
import com.nhnacademy.notice_board.objects.PostRepository;
import com.nhnacademy.notice_board.objects.User;
import com.nhnacademy.notice_board.objects.UserRepository;
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
        req.setAttribute("posts",postList);
        return "/post/postlist.jsp";
    }
}