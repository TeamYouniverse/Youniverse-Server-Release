package server.youniverse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.youniverse.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
    boolean existsByName(String name);
}
