package server.youniverse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.controller.dto.PostGetResponse;
import server.youniverse.domain.entity.Planet;
import server.youniverse.service.PostService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //글 작성
    @PostMapping("/api/v1/posts")
    public PostCreateResponse createPost(@Valid @RequestBody PostCreateRequest request) {
        Long memberId = 1L;
        return postService.createPost(memberId, request);
    }

    @GetMapping("/api/v1/posts")
    public PostGetResponse getRecentPosts(@RequestParam Planet emotion) {
        Long memberId = 1L;
        return postService.getRecentPosts(memberId, emotion);
    }

    @GetMapping("/api/v1/posts/all")
    public PostGetResponse getPostsByEmotion(@RequestParam Planet emotion, Pageable pageable) {
        Long memberId = 1L;
        return postService.getPostsByEmotion(memberId, emotion, pageable);
    }

    @DeleteMapping("/api/v1/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        Long memberId = 1L;
        postService.deletePost(memberId, postId);
    }
}
