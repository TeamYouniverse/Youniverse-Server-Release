package server.youniverse.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String name;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getName());
    }
}
