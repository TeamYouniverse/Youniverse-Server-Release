package server.youniverse.domain.entity;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String password;

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Id
    public Long getUser_id() {
        return user_id;
    }
}
