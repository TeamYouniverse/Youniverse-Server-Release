package server.youniverse.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Planet {
    happy("행복"),
    sad("서운"),
    sorry("미안"),
    touching("감동");

    private final String emotion;
}
