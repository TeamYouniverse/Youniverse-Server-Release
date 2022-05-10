package server.youniverse.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.youniverse.common.exception.ErrorCode;
import server.youniverse.common.exception.model.ValidationException;
import server.youniverse.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public void checkExistsUser(String nickname) {
        if (memberRepository.existsByName(nickname)) {
            throw new ValidationException(String.format("존재하지 않는 유저 (%s) 입니다.", nickname), ErrorCode.NONEXISTENT_MEMBER_EXCEPTION);
        }
    }
}