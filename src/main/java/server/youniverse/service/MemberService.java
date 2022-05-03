package server.youniverse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.youniverse.controller.dto.MemberResponseDto;
import server.youniverse.repository.MemberRepository;
import server.youniverse.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String name) {
        return userRepository.findByName(name)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    //SecurityContext에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        return userRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
}
