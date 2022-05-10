package server.youniverse.controller.post.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import server.youniverse.domain.post.Emotion;
import server.youniverse.service.post.dto.request.CreatePostRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePostRequest {

    @NotNull(message = "{member.memberId.notNull}")
    private Long memberId;

    @NotBlank
    private Emotion emotion;

    @NotBlank(message = "{post.nickname.notBlank}")
    private String author;

    @NotBlank(message = "{post.content.notBlank}")
    @Length(min = 10, max = 300, message = "{post.content.length}")
    private String content;

    public CreatePostRequestDto toServiceDto() {
        return CreatePostRequestDto.of(memberId, emotion, author, content);
    }
}
