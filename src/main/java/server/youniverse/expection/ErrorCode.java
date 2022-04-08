package server.youniverse.expection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static server.youniverse.expection.ErrorStatusCode.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {;
//    VALIDATION_AUTH_TOKEN_EXCEPTION(BAD_REQUEST, "만료되거나 유효하지 않은 인증 토큰입니다");

    private final ErrorStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}
