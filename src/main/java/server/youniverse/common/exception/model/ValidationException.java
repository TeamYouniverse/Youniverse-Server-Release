package server.youniverse.common.exception.model;

import server.youniverse.common.exception.ErrorCode;

public class ValidationException extends YouniverseException {

    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }
}
