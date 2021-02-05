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
public class RedLightEastRoadAction extends LightsAction {

    @Autowired
    public RedLightEastRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        if (super.getLights().getColor().equals(Lights.Color.RED)) {
            synchronized (super.getRoad().getEastRoad()) {
                if (!super.getRoad().getEastRoad().isEmpty()) {
                    Car carOnEast = super.getRoad().getEastRoad().get(0);
                    RoadSituation roadSituation = new RoadSituation();
                    roadSituation.setStatus(RoadSituation.Status.GOES);
                    roadSituation.setCarName(carOnEast.getName());
                    roadSituation.setRoads(RoadSituation.Roads.EAST);
                    super.getRoadSituationService().save(roadSituation);
                    TimeUnit.SECONDS.sleep(super.getRandom().nextInt(5)); // car is going on green
                    super.getRoad().getEastRoad().remove(0);
                    System.out.println(carOnEast + " is going on East. Have a nice trip!");
                }
            }
        } else {
            synchronized (super.getRoad()) {
                super.getRoad().wait();
            }
        }
    }
}
