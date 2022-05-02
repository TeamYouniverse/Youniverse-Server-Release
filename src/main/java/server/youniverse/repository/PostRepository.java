package server.youniverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findTop10ByMemberIdAndEmotionOrderByCreatedDateDesc(Long memberId, Planet emotion);
    Long countByMemberIdAndEmotion(Long memberId,Planet emotion);
}

