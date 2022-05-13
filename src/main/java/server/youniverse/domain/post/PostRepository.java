package server.youniverse.domain.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.post.repository.PostRepositoryCustom;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    List<Post> findByMemberIdAndStatusActive(Long memberId, Pageable pageable);
}