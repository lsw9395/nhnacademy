package com.nhnacademy.springmvc.postlist.repository;

import com.nhnacademy.springmvc.postlist.domain.UserDto;
import com.nhnacademy.springmvc.postlist.domain.UserDto2;
import com.nhnacademy.springmvc.postlist.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByName(String name);

    UserDto readById(String id);

    @Query("select u from UserEntity u where u.id =:id and u.password=:password")
    UserDto getByIdAndPwd(@Param("id") String id,@Param("password") String pwd);

    Page<UserDto> getAllBy(Pageable pageable);

    Page<UserDto2> readAllBy(Pageable pageable);

    @Query("select u from UserEntity u inner join fetch u.postEntities")
    List<UserEntity> getAllItemsWithAssociations();

    @EntityGraph("userWithPosts")
    List<UserEntity> readAllBy();
}
