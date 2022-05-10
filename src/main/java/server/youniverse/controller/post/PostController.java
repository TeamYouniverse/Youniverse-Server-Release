package server.youniverse.controller.post;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import server.youniverse.common.dto.ApiResponse;
import server.youniverse.config.interceptor.Auth;
import server.youniverse.config.resolver.UserId;
import server.youniverse.controller.post.dto.request.CreatePostRequest;
import server.youniverse.controller.post.dto.request.RetrievePostByUsernameRequest;
import server.youniverse.service.post.PostService;
import server.youniverse.service.post.dto.response.PostDetailResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PostController {

    private final PostService postService;

    @PostMapping("/v1/post")
    public ApiResponse<PostDetailResponse> createNewtPost(@Valid @RequestBody CreatePostRequest request) {
        return ApiResponse.success(postService.createNewtPost(request.toServiceDto()));
    }

    @GetMapping("/v1/post")
    public ApiResponse<List<PostDetailResponse>> getPostsByUsername(@Valid RetrievePostByUsernameRequest request, Pageable pageable) {
        return ApiResponse.success(postService.getPostsByUsername(request, pageable));
    }

    @Auth
    @DeleteMapping("/v1/post/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId, @UserId Long memberId) {
        postService.deletePost(memberId, postId);
        return ApiResponse.SUCCESS;
    }
}
