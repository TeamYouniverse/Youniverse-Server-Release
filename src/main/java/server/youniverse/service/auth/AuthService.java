package server.youniverse.service.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.youniverse.common.dto.JwtValueDto;
import server.youniverse.common.exception.ErrorCode;
import server.youniverse.common.exception.model.ConflictException;
import server.youniverse.common.exception.model.NotFoundException;
import server.youniverse.common.exception.model.ValidationException;
import server.youniverse.common.util.JwtUtils;
import server.youniverse.domain.member.Member;
import server.youniverse.domain.member.MemberRepository;
import server.youniverse.service.auth.dto.LoginRequestDto;
import server.youniverse.service.auth.dto.SignUpRequestDto;

import java.util.Objects;

import static server.youniverse.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    public JwtValueDto signup(SignUpRequestDto request) {
        if (memberRepository.existsByName(request.getNickname())) {
            throw new ConflictException(String.format("이미 존재하는 닉네임 (%s) 입니다.", request.getNickname()), CONFLICT_NICKNAME_EXCEPTION);
        }
        Member member = request.toEntity(passwordEncoder.encode(request.getPassword()));
        memberRepository.save(member);
        return jwtUtils.createTokenInfo(member.getId());
    }

    public JwtValueDto login(LoginRequestDto request) {
        Member member = memberRepository.findByNickname(request.getNickname());
        if (Objects.isNull(member)) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다.", request.getNickname()), NONEXISTENT_MEMBER_EXCEPTION);
        }
        boolean pwdMatch = passwordEncoder.matches(request.getPassword(), member.getPassword());
        if (!pwdMatch) {
            throw new ValidationException(String.format("유저 (%s) 의 비밀번호가 일치하지 않습니다.", request.getNickname()), VALIDATION_PWD_EXCEPTION);
        }
        return jwtUtils.createTokenInfo(member.getId());
    }
}
