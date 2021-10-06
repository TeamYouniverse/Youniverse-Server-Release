package server.youniverse.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Post;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    private String name;
    private String password;

    @OneToMany
    private List<Post> posts;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
