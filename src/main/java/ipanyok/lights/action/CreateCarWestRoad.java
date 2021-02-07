package ipanyok.lights.action;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCarWestRoad extends CreateCarAction {

    @Autowired
    public CreateCarWestRoad(Road road, RoadSituationService roadSituationService) {
        super(road, roadSituationService);
    }

    @Override
    public void execute() {
        synchronized (super.getRoad().getWestRoad()) {
            Car car = Car.generateRandomCar();
            super.getRoad().getWestRoad().add(car);
            RoadSituation roadSituation = new RoadSituation();
            roadSituation.setCarName(car.getName());
            roadSituation.setStatus(RoadSituation.Status.WAIT);
            roadSituation.setQueuePosition(super.getRoad().getEastRoad().indexOf(car) + 1);
            roadSituation.setRoads(RoadSituation.Roads.WEST);
            super.getRoadSituationService().save(roadSituation);
            System.err.println(car + " going on West and wait for green color...");
        }
    }
}
