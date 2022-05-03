package server.youniverse.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.youniverse.domain.entity.Planet;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {
    @NotNull
    private Planet emotion;
    @NotNull
    private String nickname;
    @NotNull
    private String content;

    public PostCreateRequest(Planet emotion, String nickname, String content) {
        this.emotion = emotion;
        this.nickname = nickname;
        this.content = content;
    }
}
