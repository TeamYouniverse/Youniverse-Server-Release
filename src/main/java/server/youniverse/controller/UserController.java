package server.youniverse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.youniverse.domain.entity.User;
import server.youniverse.service.jwt.JwtServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    JwtServiceImpl jwtService;

    @GetMapping("/getUser")//토큰ㅇ 담겨있는 사용자 정보를 리턴, 토큰이 필요한 경로. 관리자페이지때 사용(추후 수정)
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        try {
            String token = request.getHeader("jwt-auth-token");
            Map<String, Object> tokenInfoMap = jwtService.getInfo(token);

            User user = new ObjectMapper().convertValue(tokenInfoMap.get("user"), User.class);

            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login") //토큰 필요 없는 경로
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response) {
        try {
            User registeredUser = new User(); //DB에서 사용자 정보 가져오기 (추후 수정)
            if (registeredUser.getId().equals(user.getId()) && registeredUser.getPassword().equals(user.getPassword())) {
                String token = jwtService.createToken(user);
                response.setHeader("jwt-auth-token", token);
                return new ResponseEntity<Object>("login Success", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
    }
}
