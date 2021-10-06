package server.youniverse.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Post;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String name;
    private String password;

    @OneToMany
    private List<Post> posts;

}
