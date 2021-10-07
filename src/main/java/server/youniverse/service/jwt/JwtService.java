package server.youniverse.service.jwt;
import org.springframework.context.annotation.Bean;
import server.youniverse.domain.entity.User;

import java.util.Map;

public interface JwtService {

    //토큰생성
    String createToken(User user);
    Map<String,Object> getInfo(String token) throws Exception;
    void checkValid(String token);
}
