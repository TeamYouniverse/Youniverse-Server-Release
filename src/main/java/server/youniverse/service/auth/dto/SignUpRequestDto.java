package server.youniverse.service.auth.dto;

import lombok.*;
import server.youniverse.domain.member.Member;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    private String nickname;

    private String password;

    public static SignUpRequestDto of(String nickname, String password) {
        return new SignUpRequestDto(nickname, password);
    }

    public Member toEntity(String encodedPwd) {
        return Member.of(nickname, encodedPwd);
    }
}