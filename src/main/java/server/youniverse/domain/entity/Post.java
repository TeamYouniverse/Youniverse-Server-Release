package server.youniverse.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import server.youniverse.domain.entity.Planet;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String nickname;
    private String contents;

    @Enumerated(EnumType.STRING)
    private Planet emotion;

    private boolean active;

    public Post(@NotBlank String nickname,@NotBlank String contents, @NotBlank Planet emotion){
        this.nickname=nickname;
        this.contents=contents;
        this.emotion=emotion;
        this.active=true;
    }
}
