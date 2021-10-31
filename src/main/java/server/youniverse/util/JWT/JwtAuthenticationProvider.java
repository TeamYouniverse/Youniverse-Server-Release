package server.youniverse.util.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//JWT 생성, 검증, 정보 추출. JWT 필터 안에서 사용
public class JwtAuthenticationProvider {
    private String secretKey="secret";

    private long tokenValidTime=1000L*60*60; //유효시간 1시간

    @Autowired
    private UserDetailsService userDetailsService;

    //JWT 토큰 생성
    public String createToken(String userPk){
        Claims claims= Jwts.claims().setSubject(userPk);//JWT payload에 저장되는 정보단위
        Date now=new Date();
        return Jwts.builder()
                .setClaims(claims)//정보 저장
                .setIssuedAt(now)//토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime()+tokenValidTime))//set Expire Time
                .signWith(SignatureAlgorithm.HS256,secretKey)//사용할 암호화 알고리즘,signature에 들어갈 secret세팅
                .compact();
    }

    //JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails =userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //Request의 Header에서 token 값 "X-AUTH-TOKEN":"TOKEN값"
    public String resolveToken(HttpServletRequest request){
        String token=null;
        Cookie cookie= WebUtils.getCookie(request,"X-AUTH-TOKEN");
        if(cookie !=null)token=cookie.getValue();
        return token;
    }
    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return  false;
        }
    }
}
