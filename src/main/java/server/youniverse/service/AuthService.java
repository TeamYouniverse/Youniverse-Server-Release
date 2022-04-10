package server.youniverse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import server.youniverse.common.ErrorCode;
import server.youniverse.controller.dto.*;
import server.youniverse.domain.entity.Member;
import server.youniverse.domain.entity.RefreshToken;
import server.youniverse.repository.MemberRepository;
import server.youniverse.repository.RefreshTokenRepository;
import server.youniverse.jwt.TokenProvider;

import static server.youniverse.common.ErrorCode.ALREADY_MEMBER_EXCEPTION;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByName(memberRequestDto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ALREADY_MEMBER_EXCEPTION.getMessage());
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        //save
        MemberResponseDto.of(memberRepository.save(member));

        //login
        return login(memberRequestDto);
    }

    public AuthDto login(MemberRequestDto memberRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        AuthDto authDto = AuthDto.builder().name(authentication.getName())
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .accessTokenExpiresIn(tokenDto.getAccessTokenExpiresIn())
                .build();
        // 5. 토큰 발급
        return authDto;
    }

    public AuthDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN.getMessage());
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN.getMessage()));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN.getMessage());
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        AuthDto authDto = AuthDto.builder().name(authentication.getName())
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .accessTokenExpiresIn(tokenDto.getAccessTokenExpiresIn())
                .build();

        // 토큰 발급
        return authDto;
    }
}
