package server.youniverse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.youniverse.controller.dto.PostCreateRequest;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;
import server.youniverse.service.PostService;
import server.youniverse.service.PostServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceImplTest {
    private PostService postService;
    private SpyPostRepository spyPostRepository;

    @BeforeEach
    void setUp() {
        spyPostRepository = new SpyPostRepository();
        postService = new PostServiceImpl(spyPostRepository);
    }

    @Test
    void createPost_callsSaveInPostRepository() {
        postService.createPost(1L, new PostCreateRequest(Planet.HAPPY, "pine", "content"));

        Post savedPost = spyPostRepository.save_argumentPost;
        assertThat(savedPost.getMemberId()).isEqualTo(1L);
        assertThat(savedPost.getNickname()).isEqualTo("pine");
        assertThat(savedPost.getContent()).isEqualTo("content");
    }
}
