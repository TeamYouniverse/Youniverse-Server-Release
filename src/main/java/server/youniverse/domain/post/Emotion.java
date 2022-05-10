package server.youniverse.domain.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Emotion {
    HAPPY("행복"),
    SAD("서운"),
    SORRY("미안"),
    TOUCHING("감동");

    private final String emotion;
}