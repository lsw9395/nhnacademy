package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import com.nhnacademy.springmvc.postlist.entity.QPostEntity;
import com.nhnacademy.springmvc.postlist.entity.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class JpaPostRepositoryImpl extends QuerydslRepositorySupport implements JpaPostRepositoryCustom {
    public JpaPostRepositoryImpl(){
        super(PostEntity.class);
    }
    @Override
    public List<PostEntity> getPostsByUserId(String id) {
        QPostEntity post = QPostEntity.postEntity;
        QUserEntity user = QUserEntity.userEntity;


        return from(post)
                .innerJoin(post.user,user).fetchJoin()
                .where(post.user.id.eq(id))
                .select(post)
                .fetch();
    }
}
