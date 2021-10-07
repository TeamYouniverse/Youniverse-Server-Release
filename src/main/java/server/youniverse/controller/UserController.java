package server.youniverse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.youniverse.domain.dto.Response;
import server.youniverse.domain.dto.UserDto;
import server.youniverse.service.jwt.JwtUserDetailsService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtUserDetailsService userService;

    @PostMapping("/signup")
    public Response signup(@RequestBody UserDto userDto){
        //회원 추가
        Response response=new Response();
        try{
            userService.save(userDto);
            response.setResponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
        }catch (Exception e){
            response.setResponse("failed");
            response.setMessage("회원가입을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());//errorMessage
        }
        return response;
    }

}
