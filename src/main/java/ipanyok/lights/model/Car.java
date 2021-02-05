package ipanyok.lights.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Car {

    private enum Marks {
        HYUNDAI, HONDA, KIA, RENAULT, BMW, BENTLEY, MERCEDES, ACCURA, VOLKSWAGEN, PEUGEOT, FERRARI, MAZERATTI, MITSUBISHI, SUZUKI, VOLVO, MAZDA, TOYOTA, LEXUS;

        private static final List<Marks> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Marks getRandomCar()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    private static int count;
    private int id;
    private String name;

    public Car(String name) {
        count++;
        this.id = count;
        this.name = name + "_" + id;
    }

    public String getName() {
        return name;
    }

    public static Car generateRandomCar() {
        return new Car(Marks.getRandomCar().name());
    }

    @Override
    public String toString() {
        return name;
    }
}
