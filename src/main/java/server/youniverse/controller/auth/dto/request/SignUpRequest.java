package server.youniverse.controller.auth.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import server.youniverse.service.auth.dto.SignUpRequestDto;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank(message = "{auth.nickname.notBlank}")
    private String nickname;

    @NotBlank(message = "{auth.password.notBlank}")
    @Length(min = 4, max = 4, message = "{auth.password.length}")
    private String password;

    public SignUpRequestDto toServiceDto() {
        return SignUpRequestDto.of(nickname, password);
    }
}
