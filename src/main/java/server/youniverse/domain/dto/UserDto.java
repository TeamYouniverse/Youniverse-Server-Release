package server.youniverse.domain.dto;

import lombok.*;
import server.youniverse.domain.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    @NotBlank(message = "우주 이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "비밀번호 형식에 맞지 않습니다")
    @Pattern(regexp = "(?=.*[0-9]).{4}",message = "비밀번호는 숫자 4개로 지정해주세요.")
    private String password;

    public UserDto(User user){
        this.name=user.getName();
        this.password=user.getPassword();
    }
}
