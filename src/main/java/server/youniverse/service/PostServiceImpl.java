package server.youniverse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.youniverse.common.exception.model.NotFoundException;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.controller.dto.PostDto;
import server.youniverse.controller.dto.PostGetResponse;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;
import server.youniverse.repository.PostRepository;


import java.util.List;
import java.util.stream.Collectors;

import static server.youniverse.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostCreateResponse createPost(Long memberId, PostCreateRequest request) {
        Post post = new Post(memberId, request.getNickname(), request.getContent(), request.getEmotion());
        postRepository.save(post);
        return PostCreateResponse.of(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostGetResponse getRecentPosts(Long memberId, Planet emotion) {
        Long postCount = postRepository.countByMemberIdAndEmotion(memberId, emotion);
        List<Post> posts = postRepository.findTop10ByMemberIdAndEmotionOrderByCreatedDateDesc(memberId, emotion);

        return new PostGetResponse(postCount, posts.stream().map(PostDto::of).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public PostGetResponse getPostsByEmotion(Long memberId, Planet emotion, Pageable pageable) {
        Long postCount = postRepository.countByMemberIdAndEmotion(memberId, emotion);
        Page<PostDto> posts = postRepository.findByMemberIdAndEmotionOrderByCreatedDateDesc(memberId, emotion, pageable).map(PostDto::of);

        return new PostGetResponse(postCount, posts.getContent());
    }

    @Override
    public void deletePost(Long memberId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("글 id (%s)가 존재하지 않습니다.", postId), NOT_FOUND_EXCEPTION));
        postRepository.delete(post);
    }
}
