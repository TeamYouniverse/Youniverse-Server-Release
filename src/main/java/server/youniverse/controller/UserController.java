package server.youniverse.controller;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import server.youniverse.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import server.youniverse.domain.dto.UserDto;
import server.youniverse.repository.UserRepository;
import server.youniverse.util.JWT.JwtAuthenticationProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;

    //회원가입
    @PostMapping("/signup")
    public void signup(@RequestBody UserDto user){

        userRepository.save(User.builder()
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .posts(null)
                .build());
    }

    //로그인
    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto user, HttpServletResponse response){
        User member=userRepository.findByName(user.getName())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 우주입니다."));
        if(!passwordEncoder.matches(user.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token= jwtAuthenticationProvider.createToken(member.getUsername());
        response.setHeader("X-AUTH-TOKEN",token);

        Cookie cookie=new Cookie("X-AUTH-TOKEN",token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new UserDto(member);
    }

    //로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie=new Cookie("X-AUTH-TOKEN",null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    //클라이언트 새로고침시 요청이 들어오는 경로. 클라이언트 토큰이 유효하면 정보 꺼냄
    @GetMapping("/info")
    public UserDto getInfo(){
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(details != null && !(details instanceof  String)) return new UserDto((User) details);
        return null;
    }

}
