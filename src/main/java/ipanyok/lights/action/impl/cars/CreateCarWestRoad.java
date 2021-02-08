package ipanyok.lights.action.impl.cars;

import ipanyok.lights.action.CreateCarAction;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import ipanyok.lights.utils.CarUtils;
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
        CarUtils.doing(super.getRoad().getWestRoad(), super.getRoadSituationService(), RoadSituation.Roads.WEST);
    }
}
