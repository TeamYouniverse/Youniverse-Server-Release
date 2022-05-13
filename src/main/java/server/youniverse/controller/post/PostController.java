package server.youniverse.controller.post;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "글 작성", notes = "행성에 글을 작성합니다.")
    @PostMapping("/v1/post")
    public ApiResponse<PostDetailResponse> createNewtPost(@Valid @RequestBody CreatePostRequest request) {
        return ApiResponse.success(postService.createNewtPost(request.toServiceDto()));
    }
    @ApiOperation(value = "행성 글 조회", notes = "해당 감정 행성의 글을 페이징하여 조회합니다.")
    @GetMapping("/v1/post")
    public ApiResponse<List<PostDetailResponse>> getPostsByUsername(@Valid RetrievePostByUsernameRequest request, Pageable pageable) {
        return ApiResponse.success(postService.getPostsByUsername(request, pageable));
    }

    @Auth
    @ApiOperation(value = "글 삭제", notes = "글을 삭제합니다.")
    @DeleteMapping("/v1/post/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId, @UserId Long memberId) {
        postService.deletePost(memberId, postId);
        return ApiResponse.SUCCESS;
    }
}
