package server.youniverse.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto{
    private String name;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
