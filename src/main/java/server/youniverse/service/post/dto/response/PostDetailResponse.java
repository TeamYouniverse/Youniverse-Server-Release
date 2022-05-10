package server.youniverse.service.post.dto.response;

import lombok.*;
import server.youniverse.common.dto.AuditingTimeResponse;
import server.youniverse.domain.post.Emotion;
import server.youniverse.domain.post.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponse extends AuditingTimeResponse {

    private Long postId;

    private Emotion emotion;
    
    private String author;

    private String content;

    public static PostDetailResponse of(Post post) {
        PostDetailResponse response = new PostDetailResponse(post.getId(), post.getEmotion(), post.getAuthor(), post.getAuthor());
        response.setAuditingTimeByEntity(post);
        return response;
    }
}
