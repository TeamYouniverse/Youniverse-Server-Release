package server.youniverse.common.dto;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtValueDto {

    private String accessToken;
    private String refreshToken;

    public static JwtValueDto of(String accessToken, String refreshToken) {
        return new JwtValueDto(accessToken, refreshToken);
    }
}