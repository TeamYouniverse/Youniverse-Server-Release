package server.youniverse.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import server.youniverse.domain.entity.User;

import java.util.Date;
import java.util.Map;

public class JwtServiceImpl implements JwtService {
    private String secretKey="myKey";
    private long exp=100L*60*60;//토큰 사용 가능 시간 : 1시간

    //토큰 생성
    public String createToken(User user){
        return Jwts.builder()
                .setHeaderParam("typ","JWT")//토큰타입
                .setSubject("userToken") //토큰제목
                .setExpiration(new Date(System.currentTimeMillis()+exp))//토큰 유효기간
                .claim("user",user)//토큰에 담을 데이터
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes())//hashing algorithm
                .compact();//직렬화, 문자열
    }
    //토큰 정보 가져오기
    public Map<String,Object> getInfo(String token) throws Exception{
        Jws<Claims> claims=null;
        try {
            claims=Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
        }catch (Exception e){
            throw new Exception();
        }
        return claims.getBody();
    }
    //interceptor 토큰 유효성 검증 메서드
    public void checkValid(String token){
        Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
    }
}
