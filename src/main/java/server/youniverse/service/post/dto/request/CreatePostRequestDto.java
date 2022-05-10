package server.youniverse.service.post.dto.request;

import lombok.*;
import server.youniverse.domain.post.Emotion;
import server.youniverse.domain.post.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePostRequestDto {

    private Long memberId;

    private Emotion emotion;

    private String author;

    private String content;

    public static CreatePostRequestDto of(Long memberId, Emotion planet, String nickname, String content) {
        return new CreatePostRequestDto(memberId, planet, nickname, content);
    }

    public Post toEntity() {
        return Post.of(memberId, author, content, emotion);
    }
}