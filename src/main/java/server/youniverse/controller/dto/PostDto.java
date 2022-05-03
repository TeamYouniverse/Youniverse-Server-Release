package server.youniverse.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    private Long postId;
    private String nickname;
    private String content;

    public static PostDto of(Post post) {
        return new PostDto(post.getId(), post.getNickname(), post.getContent());
    }
}
