package com.nhnacademy.springmvc.postlist.entity;

import com.nhnacademy.springmvc.postlist.domain.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="Posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="writer_user_id")
    private UserEntity user;
    @Column(name="title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

}
