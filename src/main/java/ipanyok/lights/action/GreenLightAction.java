package ipanyok.lights.action;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class GreenLightAction implements Runnable {

    private Road road;
    private Lights lights;
    private RoadSituationService roadSituationService;
    private Random random = new Random();

    @Autowired
    public GreenLightAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        this.road = road;
        this.lights = lights;
        this.roadSituationService = roadSituationService;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (lights.getColor().equals(Lights.Color.GREEN)) {

                    synchronized (road.getSouthRoad()) {
                        if (!road.getSouthRoad().isEmpty()) {
                            Car carOnSouth = road.getSouthRoad().get(0);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setStatus(RoadSituation.Status.GOES);
                            roadSituation.setCarName(carOnSouth.getName());
                            roadSituation.setRoads(RoadSituation.Roads.SOUTH);
                            roadSituationService.save(roadSituation);
                            TimeUnit.SECONDS.sleep(random.nextInt(5));  // car is going on green
                            road.getSouthRoad().remove(0);
                            System.out.println(carOnSouth + " is going on South. Have a nice trip!");
                        }
                    }

                    //
                    synchronized (road.getNorthRoad()) {
                        if (!road.getNorthRoad().isEmpty()) {
                            Car carOnNorth = road.getNorthRoad().get(0);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setStatus(RoadSituation.Status.GOES);
                            roadSituation.setCarName(carOnNorth.getName());
                            roadSituation.setRoads(RoadSituation.Roads.NORTH);
                            roadSituationService.save(roadSituation);
                            TimeUnit.SECONDS.sleep(random.nextInt(5));  // car is going on green
                            road.getNorthRoad().remove(0);
                            System.out.println(carOnNorth + " is going on North. Have a nice trip!");
                        }
                    }

                } else {
                    synchronized (road) {
                        road.wait();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
