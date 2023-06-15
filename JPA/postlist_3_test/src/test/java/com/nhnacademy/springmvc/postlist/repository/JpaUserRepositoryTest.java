package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.config.RootConfig;
import com.nhnacademy.springmvc.postlist.config.WebConfig;
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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class JpaUserRepositoryTest {
    @Autowired
    private JpaUserRepository repository;
    @Test
    void findByName() {
        UserEntity userEntity = repository.findByName("유저1");
        UserEntity userEntity1 = repository.findById("user1").get();
        assertThat(userEntity).isEqualTo(userEntity1);
    }
    @Test
    void test1(){
        UserEntity user = new UserEntity();
        user.setId("user6");
        user.setPassword("12345");
        user.setName("유저6");
        user.setProfileFileName("user4.jpg");
        repository.save(user);

        UserEntity user1 = repository.findByName("유저6");
        assertThat(user.getId()).isEqualTo(user1.getId());
    }
    @Test
    void test2(){
        UserEntity user = new UserEntity();
        user.setId("user6");
        user.setPassword("12345");
        user.setName("유저6");
        user.setProfileFileName("user4.jpg");
        repository.save(user);

        UserEntity user1 = repository.findById("user6").get();
        assertThat(user.getId()).isEqualTo(user1.getId());
    }
}