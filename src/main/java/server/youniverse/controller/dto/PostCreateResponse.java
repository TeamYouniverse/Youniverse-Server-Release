package server.youniverse.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateResponse {
    private Long postId;
    private String nickname;

    public static PostCreateResponse of(Post post) {
        return new PostCreateResponse(
                post.getId(),
                post.getNickname());
    }
}
