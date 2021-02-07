package ipanyok.lights.action;

import ipanyok.lights.model.Road;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class CreateCarAction implements Runnable {

    private Road road;
    private RoadSituationService roadSituationService;
    private Random random = new Random();

    @Autowired
    public CreateCarAction(Road road, RoadSituationService roadSituationService) {
        this.road = road;
        this.roadSituationService = roadSituationService;
    }

    public Road getRoad() {
        return road;
    }

    public RoadSituationService getRoadSituationService() {
        return roadSituationService;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract void execute();

}
