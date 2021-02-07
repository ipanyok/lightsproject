package ipanyok.lights.action.impl;

import ipanyok.lights.action.LightsAction;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import ipanyok.lights.utils.LightsActionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreenLightNorthRoadAction extends LightsAction {

    @Autowired
    public GreenLightNorthRoadAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        super(road, lights, roadSituationService);
    }

    @Override
    public void execute() throws Exception {
        LightsActionUtils.doing(super.getRoad(), super.getRoad().getNorthRoad(), super.getLights(), Lights.Color.GREEN, RoadSituation.Roads.NORTH, super.getRoadSituationService(), super.getRandom());
    }
}
