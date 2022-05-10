package server.youniverse.controller.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.youniverse.common.dto.ApiResponse;
import server.youniverse.common.dto.JwtValueDto;
import server.youniverse.controller.auth.dto.request.LoginRequest;
import server.youniverse.controller.auth.dto.request.SignUpRequest;
import server.youniverse.service.auth.AuthService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/auth/signup")
    public ApiResponse<JwtValueDto> signup(@RequestBody @Valid SignUpRequest request) {
        return ApiResponse.success(authService.signup(request.toServiceDto()));
    }

    @PostMapping("/v1/auth/login")
    public ApiResponse<JwtValueDto> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.success(authService.login(request.toServiceDto()));
    }

//    @PostMapping("/v1/auth/reissue")
//    public ApiResponse<JwtValueDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }
}