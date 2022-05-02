package server.youniverse.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    private String nickname;
    private String content;

    @Enumerated(EnumType.STRING)
    private Planet emotion;

    private boolean active;

    public Post(Long memberId, String nickname, String content, Planet emotion) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.content = content;
        this.emotion = emotion;
        this.active = true;
    }
}
