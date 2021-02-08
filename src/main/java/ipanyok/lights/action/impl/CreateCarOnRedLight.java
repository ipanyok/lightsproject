package ipanyok.lights.action.impl;


import ipanyok.lights.action.CreateCarAction;
import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.service.RoadSituationService;
import ipanyok.lights.utils.CarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCarOnRedLight extends CreateCarAction {

    private Lights lights;

    @Autowired
    public CreateCarOnRedLight(Road road, RoadSituationService roadSituationService, Lights lights) {
        super(road, roadSituationService);
        this.lights = lights;
    }

    @Override
    public void execute() {
        try {
            CarUtils.createCarOnRedLight(lights, super.getRoadSituationService(), super.getRandom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
