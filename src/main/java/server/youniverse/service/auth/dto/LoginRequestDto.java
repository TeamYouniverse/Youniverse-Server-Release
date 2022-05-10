package server.youniverse.service.auth.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDto {

    private String nickname;

    private String password;

    public static LoginRequestDto of(String nickname, String password) {
        return new LoginRequestDto(nickname, password);
    }
}