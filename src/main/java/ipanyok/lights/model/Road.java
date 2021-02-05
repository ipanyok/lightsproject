package ipanyok.lights.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Road {

    private List<Car> northRoad = new ArrayList<>();
    private List<Car> southRoad = new ArrayList<>();
    private List<Car> eastRoad = new ArrayList<>();
    private List<Car> westRoad = new ArrayList<>();

    public synchronized List<Car> getNorthRoad() {
        return northRoad;
    }

    public synchronized List<Car> getSouthRoad() {
        return southRoad;
    }

    public synchronized List<Car> getEastRoad() {
        return eastRoad;
    }

    public synchronized List<Car> getWestRoad() {
        return westRoad;
    }

}
