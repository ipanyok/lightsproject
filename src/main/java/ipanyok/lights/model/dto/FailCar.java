package ipanyok.lights.model.dto;

import ipanyok.lights.model.entity.RoadSituation;

import java.time.LocalDateTime;

public class FailCar {

    private String name;
    private RoadSituation.Roads direction;
    private LocalDateTime time;

    public FailCar(String name, RoadSituation.Roads direction, LocalDateTime time) {
        this.name = name;
        this.direction = direction;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public RoadSituation.Roads getDirection() {
        return direction;
    }

    public void setDirection(RoadSituation.Roads direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
