package server.youniverse.config.security;

public abstract class JwtConstants {

    public static final String USER_ID = "USER_ID";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;              // 30분
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일
}