package server.youniverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}
