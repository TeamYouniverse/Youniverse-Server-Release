package server.youniverse.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.youniverse.domain.common.AuditingTimeEntity;

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
public class Post extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String author;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Builder(access = AccessLevel.PUBLIC)
    private Post(Long memberId, String author, String content, Emotion emotion, PostStatus status) {
        this.memberId = memberId;
        this.author = author;
        this.content = content;
        this.emotion = emotion;
        this.status = status;
    }

    public static Post of(Long memberId, String author, String content, Emotion emotion) {
        return Post.builder()
                .memberId(memberId)
                .author(author)
                .content(content)
                .emotion(emotion)
                .status(PostStatus.ACTIVE)
                .build();
    }
}
