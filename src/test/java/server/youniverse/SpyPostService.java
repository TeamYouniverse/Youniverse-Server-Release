//package server.youniverse;
//
//import org.springframework.data.domain.Pageable;
//import server.youniverse.controller.post.dto.request.CreatePostRequest;
//import server.youniverse.service.post.dto.PostCreateResponse;
//import server.youniverse.controller.post.dto.PostGetResponse;
//import server.youniverse.domain.planet.Planet;
//
//public class SpyPostService implements PostService {
//    public String createPost_argumentNickname;
//    public PostCreateResponse createPost_returnValue;
//
//    @Override
//    public PostCreateResponse createPost(Long memberId, CreatePostRequest request) {
//        createPost_argumentNickname = request.getNickname();
//        return createPost_returnValue;
//    }
//
//    @Override
//    public PostGetResponse getRecentPosts(Long memberId, Planet emotion) {
//        return null;
//    }
//
//    @Override
//    public PostGetResponse getPostsByEmotion(Long memberId, Planet emotion, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public void deletePost(Long memberId, Long postId) {
//
//    }
//}
