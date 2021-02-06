package ipanyok.lights.listener;


import ipanyok.lights.action.CreateCarAction;
import ipanyok.lights.action.LightsAction;
import ipanyok.lights.model.Lights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RefreshContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Lights lights;

    @Autowired
    private CreateCarAction createCar;

    @Autowired
    private List<LightsAction> actions;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Start with " + lights.getColor());
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(lights);
        executor.execute(createCar);

        actions.forEach(action -> executor.execute(action));

        executor.shutdown();

    }
}

