package ipanyok.lights.action;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCarSouthRoad extends CreateCarAction {

    @Autowired
    public CreateCarSouthRoad(Road road, RoadSituationService roadSituationService) {
        super(road, roadSituationService);
    }

    @Override
    public void execute() {
        synchronized (super.getRoad().getSouthRoad()) {
            Car car = Car.generateRandomCar();
            super.getRoad().getSouthRoad().add(car);
            RoadSituation roadSituation = new RoadSituation();
            roadSituation.setCarName(car.getName());
            roadSituation.setStatus(RoadSituation.Status.WAIT);
            roadSituation.setQueuePosition(super.getRoad().getSouthRoad().indexOf(car) + 1);
            roadSituation.setRoads(RoadSituation.Roads.SOUTH);
            super.getRoadSituationService().save(roadSituation);
            System.err.println(car + " going on South and wait for green color...");
        }
    }
}