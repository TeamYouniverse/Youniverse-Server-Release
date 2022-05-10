package server.youniverse.controller.member;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import server.youniverse.common.dto.ApiResponse;
import server.youniverse.service.MemberService;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/v1/member/{nickname}")
    public ApiResponse<String> checkExistsUser(@PathVariable String nickname) {
        memberService.checkExistsUser(nickname);
        return ApiResponse.SUCCESS;
    }
}