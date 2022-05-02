package server.youniverse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.controller.dto.PostDto;
import server.youniverse.controller.dto.PostGetResponse;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;
import server.youniverse.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostCreateResponse createPost(Long memberId, PostCreateRequest request) {
        Post post = new Post(memberId, request.getNickname(), request.getContent(), request.getPlanet());
        postRepository.save(post);
        return PostCreateResponse.of(post);
    }

    @Override
    public PostGetResponse getRecentPosts(Long memberId, Planet emotion) {
        Long postCount = postRepository.countByMemberIdAndEmotion(memberId, emotion);
        List<Post> posts = postRepository.findTop10ByMemberIdAndEmotionOrderByCreatedDateDesc(memberId, emotion);
        return new PostGetResponse(postCount, posts.stream().map(PostDto::of).collect(Collectors.toList()));
    }

    @Override
    public PostGetResponse getPostsByEmotion(Long memberId, Planet emotion, Pageable pageable) {
        Long postCount = postRepository.countByMemberIdAndEmotion(memberId, emotion);
        Page<Post> posts = postRepository.findByMemberIdAndEmotionOrderByCreatedDateDesc(memberId, emotion, pageable);

        return new PostGetResponse(postCount, posts.stream().map(PostDto::of).collect(Collectors.toList()));
    }
}
