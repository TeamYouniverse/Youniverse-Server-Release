package server.youniverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
