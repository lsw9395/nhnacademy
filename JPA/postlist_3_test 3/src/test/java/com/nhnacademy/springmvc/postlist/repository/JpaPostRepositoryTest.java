package com.nhnacademy.springmvc.postlist.repository;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
import com.nhnacademy.springmvc.postlist.domain.Post;
import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class JpaPostRepositoryTest {
    @Autowired
    private JpaPostRepository postRepository;
    @Autowired
    private JpaUserRepository userRepository;

    @Test
    void Test(){
        UserEntity user = new UserEntity();
        user.setId("user101");
        user.setPassword("12345");
        user.setName("유저101");
        user.setProfileFileName("user101.jpg");
        userRepository.save(user);

        PostEntity post = new PostEntity();
        post.setUser(user);
        post.setTitle("제목101");
        post.setContent("내용101");
        post.setWriteTime(LocalDateTime.now());
        post.setViewCount(0);
        postRepository.saveAndFlush(post);

        List<PostEntity> postEntities = postRepository.findByUser_Id("user101");
        assertThat(postEntities.get(0)).isEqualTo(post);
    }
    @Test
    void findByUser_name() {
        List<PostEntity> postEntities = postRepository.findByUser_Name("유저1");
        assertThat(postEntities).isNotEmpty().hasSize(2);
    }


    @Test
    void findByTitleLike() {
        List<PostEntity> postEntities = postRepository.findByTitleLike("제목1");
        assertThat(postEntities).isNotEmpty().hasSize(1);
        PostEntity postEntity = postRepository.findById(1l).get();
        assertThat(postEntities.get(0)).isEqualTo(postEntity);
    }

    @Test
    void findByContentLike() {
        List<PostEntity> postEntities = postRepository.findByContentLike("내용2");
        assertThat(postEntities).isNotEmpty().hasSize(1);
        PostEntity postEntity = postRepository.findById(2l).get();
        assertThat(postEntities.get(0)).isEqualTo(postEntity);
    }

    @Test
    void findByTitleLikeAndContentLike() {
        List<PostEntity> postEntities = postRepository.findByTitleLikeAndContentLike("제목1","내용1");
        assertThat(postEntities).isNotEmpty().hasSize(1);
        PostEntity postEntity = postRepository.findById(1l).get();
        assertThat(postEntities.get(0)).isEqualTo(postEntity);
    }
}