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
public class RedLightWestRoadAction extends LightsAction {

    @Autowired
    public RedLightWestRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        if (super.getLights().getColor().equals(Lights.Color.RED)) {
            synchronized (super.getRoad().getWestRoad()) {
                if (!super.getRoad().getWestRoad().isEmpty()) {
                    Car carOnWest = super.getRoad().getWestRoad().get(0);
                    RoadSituation roadSituation = new RoadSituation();
                    roadSituation.setStatus(RoadSituation.Status.GOES);
                    roadSituation.setCarName(carOnWest.getName());
                    roadSituation.setRoads(RoadSituation.Roads.WEST);
                    super.getRoadSituationService().save(roadSituation);
                    TimeUnit.SECONDS.sleep(super.getRandom().nextInt(5)); // car is going on green
                    super.getRoad().getWestRoad().remove(0);
                    System.out.println(carOnWest + " is going on West. Have a nice trip!");
                }
            }
        } else {
            synchronized (super.getRoad()) {
                super.getRoad().wait();
            }
        }
    }
}
