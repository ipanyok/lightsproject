package ipanyok.lights.utils;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LightsActionUtils {

    public static void doing (Road road, List<Car> carsOnRoad, Lights lights, Lights.Color color, RoadSituation.Roads roads, RoadSituationService roadSituationService, Random random) throws Exception {
        if (lights.getColor().equals(color)) {
            synchronized (carsOnRoad) {
                if (!carsOnRoad.isEmpty()) {
                    Car carOnNorth = carsOnRoad.get(0);
                    RoadSituation roadSituation = new RoadSituation();
                    roadSituation.setStatus(RoadSituation.Status.GOES);
                    roadSituation.setCarName(carOnNorth.getName());
                    roadSituation.setRoads(roads);
                    roadSituationService.save(roadSituation);
                    TimeUnit.SECONDS.sleep(random.nextInt(4));  // car is going on green
                    carsOnRoad.remove(0);
                    String log = String.format(carOnNorth + " is going on %s. Have a nice trip!", roads);
                    System.out.println(log);
                }
            }
        } else {
            synchronized (road) {
                road.wait();
            }
        }
    }
}
