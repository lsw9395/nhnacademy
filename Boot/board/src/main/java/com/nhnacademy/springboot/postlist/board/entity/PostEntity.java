package com.nhnacademy.springboot.postlist.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @JoinColumn(name="writerUserId")
    private UserEntity user;
    @Column(name="title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writeTime")
    private LocalDateTime writeTime;
    @Column(name = "viewCount")
    private int viewCount;

}
