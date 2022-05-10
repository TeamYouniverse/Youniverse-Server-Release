package server.youniverse.common.exception.model;

import server.youniverse.common.exception.ErrorCode;

public class ConflictException extends YouniverseException{

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictException(String message) {
        super(message, ErrorCode.CONFLICT_EXCEPTION);
    }
}
