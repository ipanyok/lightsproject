package ipanyok.lights.action;

import ipanyok.lights.model.Lights;
import ipanyok.lights.model.Road;
import ipanyok.lights.service.RoadSituationService;

import java.util.Random;

public abstract class LightsAction implements Runnable {

    private Road road;
    private Lights lights;
    private RoadSituationService roadSituationService;
    private Random random = new Random();

    public LightsAction(Road road, Lights lights, RoadSituationService roadSituationService) {
        this.road = road;
        this.lights = lights;
        this.roadSituationService = roadSituationService;
    }

    public Road getRoad() {
        return road;
    }

    public Lights getLights() {
        return lights;
    }

    public RoadSituationService getRoadSituationService() {
        return roadSituationService;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract void execute() throws Exception;

}
