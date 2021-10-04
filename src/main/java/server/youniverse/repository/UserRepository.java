package server.youniverse.repository;

import server.youniverse.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
    User findByName(String name);
}
