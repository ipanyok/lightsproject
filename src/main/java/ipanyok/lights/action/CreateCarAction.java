package ipanyok.lights.action;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CreateCarAction implements Runnable {

    private Road road;
    private RoadSituationService roadSituationService;
    private Random random = new Random();

    @Autowired
    public CreateCarAction(Road road, RoadSituationService roadSituationService) {
        this.road = road;
        this.roadSituationService = roadSituationService;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(random.nextInt(3));
                Car car = Car.generateRandomCar();

                int value = random.nextInt(4);
                switch (value) {
                    case 0: {
                        synchronized (road.getEastRoad()) {
                            road.getEastRoad().add(car);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setCarName(car.getName());
                            roadSituation.setStatus(RoadSituation.Status.WAIT);
                            roadSituation.setQueuePosition(road.getEastRoad().indexOf(car) + 1);
                            roadSituation.setRoads(RoadSituation.Roads.EAST);
                            roadSituationService.save(roadSituation);
                            System.err.println(car + " going on East and wait for green color...");
                            break;
                        }
                    }
                    case 1: {
                        synchronized (road.getWestRoad()) {
                            road.getWestRoad().add(car);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setCarName(car.getName());
                            roadSituation.setStatus(RoadSituation.Status.WAIT);
                            roadSituation.setQueuePosition(road.getWestRoad().indexOf(car) + 1);
                            roadSituation.setRoads(RoadSituation.Roads.WEST);
                            roadSituationService.save(roadSituation);
                            System.err.println(car + " going on West and wait for green color...");
                            break;
                        }
                    }
                    case 2: {
                        synchronized (road.getNorthRoad()) {
                            road.getNorthRoad().add(car);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setCarName(car.getName());
                            roadSituation.setStatus(RoadSituation.Status.WAIT);
                            roadSituation.setQueuePosition(road.getNorthRoad().indexOf(car) + 1);
                            roadSituation.setRoads(RoadSituation.Roads.NORTH);
                            roadSituationService.save(roadSituation);
                            System.err.println(car + " going on North and wait for green color...");
                            break;
                        }
                    }
                    case 3: {
                        synchronized (road.getSouthRoad()) {
                            road.getSouthRoad().add(car);
                            RoadSituation roadSituation = new RoadSituation();
                            roadSituation.setCarName(car.getName());
                            roadSituation.setStatus(RoadSituation.Status.WAIT);
                            roadSituation.setQueuePosition(road.getSouthRoad().indexOf(car) + 1);
                            roadSituation.setRoads(RoadSituation.Roads.SOUTH);
                            roadSituationService.save(roadSituation);
                            System.err.println(car + " going on South and wait for green color...");
                            break;
                        }
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
