package ipanyok.lights.listener;


import ipanyok.lights.action.CreateCarAction;
import ipanyok.lights.action.GreenLightAction;
import ipanyok.lights.model.Lights;
import ipanyok.lights.action.RedLightAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RefreshContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Lights lights;

    @Autowired
    private CreateCarAction createCar;

    @Autowired
    private GreenLightAction greenLightAction;

    @Autowired
    private RedLightAction redLightAction;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Start with " + lights.getColor());
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(lights);
        executor.execute(createCar);
        executor.execute(greenLightAction);
        executor.execute(redLightAction);
        executor.shutdown();
    }
}

