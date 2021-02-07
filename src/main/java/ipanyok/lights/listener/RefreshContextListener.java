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
    private List<CreateCarAction> createCarActions;

    @Autowired
    private List<LightsAction> lightsActions;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Start with " + lights.getColor());
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(lights);

        createCarActions.forEach(action -> executor.execute(action));
        lightsActions.forEach(action -> executor.execute(action));

        executor.shutdown();

    }
}

