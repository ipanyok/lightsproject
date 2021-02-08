package ipanyok.lights.action.impl.lights;

import ipanyok.lights.action.LightsAction;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import ipanyok.lights.utils.LightsActionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedLightEastRoadAction extends LightsAction {

    @Autowired
    public RedLightEastRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        LightsActionUtils.doing(super.getRoad(), super.getRoad().getEastRoad(), super.getLights(), Lights.Color.RED, RoadSituation.Roads.EAST, super.getRoadSituationService(), super.getRandom());
    }
}
