package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.domain.PostDto;
import com.nhnacademy.springmvc.postlist.domain.PostDto2;
import com.nhnacademy.springmvc.postlist.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity,Long>,JpaPostRepositoryCustom {
    List<PostEntity> findByUser_Name(String userName);
    List<PostEntity> findByUser_Id(String userId);
    List<PostEntity> findByTitleLike(String title);
    List<PostEntity> findByContentLike(String content);
    List<PostEntity> findByTitleLikeAndContentLike(String title, String content);
    List<PostDto> findByUser_id(String userId);
    Page<PostDto> getAllBy(Pageable pageable);

    Page<PostDto2> readAllBy(Pageable pageable);

    @Query("select p from PostEntity p where p.viewCount > ?1")
    List<PostDto2> getPostsHavingViewCountAtLeast(int viewCount);


}
