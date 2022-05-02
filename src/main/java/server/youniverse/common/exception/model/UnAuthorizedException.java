package server.youniverse.common.exception.model;

import server.youniverse.common.exception.ErrorCode;

public class UnAuthorizedException extends YouniverseException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}