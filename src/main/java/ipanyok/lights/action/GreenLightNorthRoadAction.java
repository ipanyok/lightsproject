package ipanyok.lights.action;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GreenLightNorthRoadAction extends LightsAction {

    @Autowired
    public GreenLightNorthRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        if (super.getLights().getColor().equals(Lights.Color.GREEN)) {
            synchronized (super.getRoad().getNorthRoad()) {
                if (!super.getRoad().getNorthRoad().isEmpty()) {
                    Car carOnNorth = super.getRoad().getNorthRoad().get(0);
                    RoadSituation roadSituation = new RoadSituation();
                    roadSituation.setStatus(RoadSituation.Status.GOES);
                    roadSituation.setCarName(carOnNorth.getName());
                    roadSituation.setRoads(RoadSituation.Roads.NORTH);
                    super.getRoadSituationService().save(roadSituation);
                    TimeUnit.SECONDS.sleep(super.getRandom().nextInt(5));  // car is going on green
                    super.getRoad().getNorthRoad().remove(0);
                    System.out.println(carOnNorth + " is going on North. Have a nice trip!");
                }
            }
        } else {
            synchronized (super.getRoad()) {
                super.getRoad().wait();
            }
        }
    }
}
