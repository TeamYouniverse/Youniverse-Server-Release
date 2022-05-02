package server.youniverse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import server.youniverse.controller.PostController;
import server.youniverse.controller.dto.PostCreateResponse;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostApiTest {
    private MockMvc mockMvc;
    private SpyPostService spyPostService;

    @BeforeEach
    void setUp() {
        spyPostService = new SpyPostService();
        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(spyPostService)).build();
    }

    @Test
    void createPost_returnsOkHttpStatus() throws Exception {
        mockMvc.perform(post("/api/v1/posts")
                        .header("AUTH_TOKEN", "givenToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"planet\":\"SORRY\",\"nickname\":\"pine\",\"content\":\"post content\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void createPost_passesArgumentsToService() throws Exception {
        mockMvc.perform(post("/api/v1/posts")
                        .header("AUTH_TOKEN", "givenToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"planet\":\"SORRY\",\"nickname\":\"pine\",\"content\":\"post content\"}"))
                .andExpect(status().isOk());

        Assertions.assertThat(spyPostService.createPost_argumentNickname);
    }

    @Test
    void createPost_returnsResponse() throws Exception {
        spyPostService.createPost_returnValue = PostCreateResponse.of(new Post(1L, "pine", "post content", Planet.SORRY));

        mockMvc.perform(post("/api/v1/posts")
                        .header("AUTH_TOKEN", "givenToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"planet\":\"SORRY\",\"nickname\":\"pine\",\"content\":\"post content\"}"))
                .andExpect(jsonPath("$.postId", equalTo(null)))
                .andExpect(jsonPath("$.nickname", equalTo("pine")));
    }
    
}
