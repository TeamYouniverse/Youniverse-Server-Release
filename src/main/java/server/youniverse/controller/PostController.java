package server.youniverse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
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

    //get /?emotion=happy //(특정 행성의 최신 글 10개, 글 전체 개수 불러오기)

    //GET /?emotion=happy&page=<>&offset=<>  별자리 클릭 && 관리자 페이지 무한스크롤

    //DELETE  /?id=  글 삭제

}
