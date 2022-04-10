package server.youniverse.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String nickname;
    private String contents;

    @Enumerated(EnumType.STRING)
    private Planet emotion;

    private boolean active;

    public Post(String nickname, String contents, Planet emotion) {
        this.nickname = nickname;
        this.contents = contents;
        this.emotion = emotion;
        this.active = true;
    }
}
