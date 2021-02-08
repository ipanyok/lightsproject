package ipanyok.lights.utils;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    public static void createCarOnRedLight(Lights lights, RoadSituationService roadSituationService, Random random) throws Exception {
        TimeUnit.SECONDS.sleep(55);
        Car car = Car.generateRandomCar();
        if (lights.getColor().equals(Lights.Color.RED)) {
            if (random.nextBoolean()) {
                onRedLightCarAction(car, RoadSituation.Roads.SOUTH, roadSituationService);
            } else {
                onRedLightCarAction(car, RoadSituation.Roads.NORTH, roadSituationService);
            }
        } else {
            if (random.nextBoolean()) {
                onRedLightCarAction(car, RoadSituation.Roads.WEST, roadSituationService);
            } else {
                onRedLightCarAction(car, RoadSituation.Roads.EAST, roadSituationService);
            }
        }

    }

    private static void onRedLightCarAction(Car car, RoadSituation.Roads roads, RoadSituationService roadSituationService) {
        RoadSituation roadSituation = new RoadSituation();
        roadSituation.setCarName(car.getName());
        roadSituation.setStatus(RoadSituation.Status.GOES_ON_RED);
        roadSituation.setRoads(roads);
        roadSituationService.save(roadSituation);
        String log = String.format(car + " going on red color!!!! " + car + " is going on %s", roads).toUpperCase();
        System.err.println(log);
    }

}
