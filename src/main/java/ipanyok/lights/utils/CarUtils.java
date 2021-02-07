package ipanyok.lights.utils;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;

import java.util.List;

public class CarUtils {

    public static void doing (List<Car> carsOnRoad, RoadSituationService roadSituationService, RoadSituation.Roads roads) {
        synchronized (carsOnRoad) {
            Car car = Car.generateRandomCar();
            carsOnRoad.add(car);
            RoadSituation roadSituation = new RoadSituation();
            roadSituation.setCarName(car.getName());
            roadSituation.setStatus(RoadSituation.Status.WAIT);
            roadSituation.setQueuePosition(carsOnRoad.indexOf(car) + 1);
            roadSituation.setRoads(roads);
            roadSituationService.save(roadSituation);
            String log = String.format(car + " going on %s and wait for green color...", roads.name());
            System.err.println(log);
        }
    }
}
