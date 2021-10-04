package server.youniverse.domain.entity;

public enum Planet {
    HAPPY("행복"),
    SAD("서운"),
    SORRY("미안"),
    TOUCHING("감동");

    private String emotion;

    Planet(String emotion){
        this.emotion=emotion;
    }
}
