package ipanyok.lights.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RoadSituation {

    public enum Status {WAIT, GOES}
    public enum Roads {EAST, WEST, NORTH, SOUTH}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LOGIN_SEQUENCE")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CAR_NAME")
    private String carName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROAD_WAY")
    private Roads roads;

    @Column(name = "QUEUE_POSITION")
    private Integer queuePosition;

    @CreationTimestamp
    private LocalDateTime time;

    public RoadSituation() {
    }

    public RoadSituation(String carName, Status status, LocalDateTime time) {
        this.carName = carName;
        this.status = status;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(Integer queuePosition) {
        this.queuePosition = queuePosition;
    }

    public String getCarName() {
        return carName;
    }

    public Roads getRoads() {
        return roads;
    }

    public void setRoads(Roads roads) {
        this.roads = roads;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
