package server.youniverse.controller.post.dto.request;

import lombok.*;
import server.youniverse.domain.post.Emotion;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrievePostByUsernameRequest {

    @NotBlank(message = "{post.nickname.notBlank}")
    private String nickname;

    private Emotion emotion;
}
