package server.youniverse.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostGetResponse {
    private Long totalCount;
    private List<PostDto> stars;

    public PostGetResponse(Long totalCount, List<PostDto> posts) {
        this.totalCount = totalCount;
        this.stars = posts;
    }
}
