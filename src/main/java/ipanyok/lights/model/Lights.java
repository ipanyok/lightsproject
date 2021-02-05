package ipanyok.lights.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Lights implements Runnable {

    public enum Color {RED, GREEN}

    private Color color;

    private Road road;

    @Autowired
    public Lights(Road road) {
        this.road = road;
        this.color = Color.RED;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(20);
                if (getColor().equals(Color.RED)) {
                    setColor(Color.GREEN);
                } else {
                    setColor(Color.RED);
                }
                System.out.println("Lights is " + getColor());
                synchronized (road) {
                    road.notifyAll();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

