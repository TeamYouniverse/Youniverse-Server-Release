package server.youniverse.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    /*토큰 관련 설정
    *토큰 발급, 자격 증명
     */
    private static  final long serialVersionUID =-2550185165626007488L; //??

    public static final long JWT_TOKEN_VALIDITY=10*60*60; //토큰 유효시간 : 1시간


    @org.springframework.beans.factory.annotation.Value("${spring.jwt.secret}")
    private String secret;

    //token에서 name정보 획득
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //token에서 만료일자 획득
    public Date getEXpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //토큰 정보 전체
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //토큰 만료 확인
    private Boolean isTokenExpired(String token){
        final Date expiration =getEXpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //유저 토큰 발급
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>(0);
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String subject){
        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    //유저 토큰 인증
    public Boolean validateToken(String token,UserDetails userDetails){
        final String name=getUsernameFromToken(token);
                return(name.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

}


