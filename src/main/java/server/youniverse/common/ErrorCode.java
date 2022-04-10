package server.youniverse.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    //400 Bad Request
    ALREADY_MEMBER_EXCEPTION("이미 존재하는 우주 이름입니다."),
    INVALID_TOKEN("토큰이 유효하지 않습니다."),
    ;
    private final String message;

}