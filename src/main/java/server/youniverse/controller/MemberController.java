package server.youniverse.controller;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.youniverse.controller.dto.MemberResponseDto;
import server.youniverse.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
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
}
