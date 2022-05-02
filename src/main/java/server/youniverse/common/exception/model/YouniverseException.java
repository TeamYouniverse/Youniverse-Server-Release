package server.youniverse.common.exception.model;

import lombok.Getter;
import server.youniverse.common.exception.ErrorCode;

@Getter
public abstract class YouniverseException extends RuntimeException {

    private final ErrorCode errorCode;

    public YouniverseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}