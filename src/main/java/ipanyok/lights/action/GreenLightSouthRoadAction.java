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
public class GreenLightSouthRoadAction extends LightsAction {

    @Autowired
    public GreenLightSouthRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        if (super.getLights().getColor().equals(Lights.Color.GREEN)) {
            synchronized (super.getRoad().getSouthRoad()) {
                if (!super.getRoad().getSouthRoad().isEmpty()) {
                    Car carOnSouth = super.getRoad().getSouthRoad().get(0);
                    RoadSituation roadSituation = new RoadSituation();
                    roadSituation.setStatus(RoadSituation.Status.GOES);
                    roadSituation.setCarName(carOnSouth.getName());
                    roadSituation.setRoads(RoadSituation.Roads.SOUTH);
                    super.getRoadSituationService().save(roadSituation);
                    TimeUnit.SECONDS.sleep(super.getRandom().nextInt(5));  // car is going on green
                    super.getRoad().getSouthRoad().remove(0);
                    System.out.println(carOnSouth + " is going on South. Have a nice trip!");
                }
            }
        } else {
            synchronized (super.getRoad()) {
                super.getRoad().wait();
            }
        }
    }
}
