package server.youniverse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.youniverse.common.dto.ApiResponse;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.controller.dto.PostGetResponse;
import server.youniverse.domain.entity.Planet;
import server.youniverse.service.PostService;

import javax.validation.Valid;

@Api(tags = {"Post"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "글 작성", notes = "행성에 글을 작성합니다.")
    @PostMapping("/")
    public ApiResponse<PostCreateResponse> createPost(@Valid @RequestBody PostCreateRequest request) {
        Long memberId = 1L;
        return ApiResponse.success(postService.createPost(memberId, request));
    }

    @ApiOperation(value = "최근 10개 글 조회", notes = "별을 눌렀을 때 감정에 맞는 최근 10개의 글을 조회할 수 있습니다.")
    @GetMapping("/")
    public ApiResponse<PostGetResponse> getRecentPosts(@RequestParam Planet emotion) {
        Long memberId = 1L;
        return ApiResponse.success(postService.getRecentPosts(memberId, emotion));
    }

    @ApiOperation(value = "행성 글 조회", notes = "해당 감정 행성의 글을 페이징하여 조회합니다.")
    @GetMapping("/all")
    public ApiResponse<PostGetResponse> getPostsByEmotion(@RequestParam Planet emotion, Pageable pageable) {
        Long memberId = 1L;
        return ApiResponse.success(postService.getPostsByEmotion(memberId, emotion, pageable));
    }

    @ApiOperation(value = "글 삭제", notes = "글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId) {
        Long memberId = 1L;
        postService.deletePost(memberId, postId);
        return ApiResponse.SUCCESS;
    }
}
