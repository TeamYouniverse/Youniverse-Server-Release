package server.youniverse.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import server.youniverse.domain.entity.Authority;
import server.youniverse.domain.entity.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String password;
    
    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(name,password);
    }
}
