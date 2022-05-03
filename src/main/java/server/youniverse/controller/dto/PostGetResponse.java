package server.youniverse.controller.dto;

import java.util.List;
import java.util.stream.Stream;

public class PostGetResponse {
    private Long totalCount;
    private List<PostDto> stars;

    public PostGetResponse(Long totalCount, List<PostDto> posts) {
        this.totalCount = totalCount;
        this.stars = posts;
    }
}
