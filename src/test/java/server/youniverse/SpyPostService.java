package server.youniverse;

import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.service.PostService;

public class SpyPostService implements PostService {
    public String createPost_argumentNickname;
    public PostCreateResponse createPost_returnValue;

    @Override
    public PostCreateResponse createPost(Long memberId, PostCreateRequest request) {
        createPost_argumentNickname=request.getNickname();
        return createPost_returnValue;
    }
}
