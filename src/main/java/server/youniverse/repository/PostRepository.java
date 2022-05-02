package server.youniverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
