package server.youniverse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.Planet;
import server.youniverse.domain.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop10ByMemberIdAndEmotionAndActiveTrueOrderByCreatedDateDesc(Long memberId, Planet emotion);

    Page<Post> findByMemberIdAndEmotionAndActiveTrueOrderByCreatedDateDesc(Long memberId, Planet emotion, Pageable pageable);

    Long countByMemberIdAndEmotion(Long memberId, Planet emotion);
}

