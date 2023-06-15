package com.nhnacademy.springmvc.postlist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NamedEntityGraphs({
        @NamedEntityGraph(name="userWithPosts",attributeNodes = {
                @NamedAttributeNode("postEntities")
        })
})
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="Users")
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name="profile_file_name")
    private String profileFileName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<PostEntity> postEntities;
}
