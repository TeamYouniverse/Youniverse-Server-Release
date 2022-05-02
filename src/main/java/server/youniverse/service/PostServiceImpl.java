package server.youniverse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.domain.entity.Post;
import server.youniverse.repository.PostRepository;

import javax.transaction.Transactional;

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
}
