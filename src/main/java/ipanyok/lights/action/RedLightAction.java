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
public class RedLightAction implements Runnable {

    private Road road;
    private Lights lights;
    private RoadSituationService roadSituationService;
    private Random random = new Random();

    @Autowired
    public RedLightAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        this.road = road;
        this.lights = lights;
        this.roadSituationService = roadSituationService;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (lights.getColor().equals(Lights.Color.RED)) {

                    synchronized (road.getEastRoad()) {
                        if (!road.getEastRoad().isEmpty()) {
                            Car carOnEast = road.getEastRoad().get(0);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setStatus(RoadSituation.Status.GOES);
                            roadSituation.setCarName(carOnEast.getName());
                            roadSituation.setRoads(RoadSituation.Roads.EAST);
                            roadSituationService.save(roadSituation);
                            TimeUnit.SECONDS.sleep(random.nextInt(5)); // car is going on green
                            road.getEastRoad().remove(0);
                            System.out.println(carOnEast + " is going on East. Have a nice trip!");
                        }
                    }

                    synchronized (road.getWestRoad()) {
                        if (!road.getWestRoad().isEmpty()) {
                            Car carOnWest = road.getWestRoad().get(0);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setStatus(RoadSituation.Status.GOES);
                            roadSituation.setCarName(carOnWest.getName());
                            roadSituation.setRoads(RoadSituation.Roads.WEST);
                            roadSituationService.save(roadSituation);
                            TimeUnit.SECONDS.sleep(random.nextInt(5)); // car is going on green
                            road.getWestRoad().remove(0);
                            System.out.println(carOnWest + " is going on West. Have a nice trip!");
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
