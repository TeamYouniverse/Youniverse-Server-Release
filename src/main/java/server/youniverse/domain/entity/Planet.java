package server.youniverse.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Planet {
    HAPPY("행복"),
    SAD("서운"),
    SORRY("미안"),
    TOUCHING("감동");

    private final String emotion;
}
