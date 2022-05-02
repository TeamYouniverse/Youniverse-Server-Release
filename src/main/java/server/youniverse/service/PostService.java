package server.youniverse.service;

import org.springframework.data.domain.Pageable;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.controller.dto.PostGetResponse;
import server.youniverse.domain.entity.Planet;

public interface PostService {
    PostCreateResponse createPost(Long memberId, PostCreateRequest request);

    PostGetResponse getRecentPosts(Long memberId, Planet emotion);

    PostGetResponse getPostsByEmotion(Long memberId, Planet emotion, Pageable pageable);
}
