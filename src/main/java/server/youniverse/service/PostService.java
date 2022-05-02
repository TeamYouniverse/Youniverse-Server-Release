package server.youniverse.service;

import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;

public interface PostService {
    PostCreateResponse createPost(Long memberId, PostCreateRequest request);
}
