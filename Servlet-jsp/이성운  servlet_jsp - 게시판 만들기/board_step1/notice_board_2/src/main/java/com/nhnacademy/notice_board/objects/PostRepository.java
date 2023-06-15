package com.nhnacademy.notice_board.objects;

import java.util.List;

public interface PostRepository {
    long register(Post post);
    void modify(Post post);
    void remove(long id);

    Post getPost(long id);
    List<Post> getPosts();
}
