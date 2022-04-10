package server.youniverse.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.youniverse.controller.dto.MemberResponseDto;
import server.youniverse.service.MemberService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{name}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String name) {
        return ResponseEntity.ok(memberService.getMemberInfo(name));
    }

//
//    //회원가입
//    @PostMapping("/signup")
//    public void signup(@RequestBody UserDto user){
//
//        userRepository.save(User.builder()
//                .name(user.getName())
//                .password(passwordEncoder.encode(user.getPassword()))
//                .posts(null)
//                .build());
//    }

//    //로그인
//    @PostMapping("/login")
//    public UserDto login(@RequestBody UserDto user, HttpServletResponse response){
//        User member=userRepository.findByName(user.getName())
//                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 우주입니다."));
//        if(!passwordEncoder.matches(user.getPassword(),member.getPassword())){
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        String token= jwtAuthenticationProvider.createToken(member.getUsername());
//        response.setHeader("X-AUTH-TOKEN",token);
//
//        Cookie cookie=new Cookie("X-AUTH-TOKEN",token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
//
//        return new UserDto(member);
//    }

    //로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
