package ipanyok.lights.model.dto;


import java.util.ArrayList;
import java.util.List;

public class CarsOnRedDto {

    private List<FailCar> cars = new ArrayList<>();

    public CarsOnRedDto(List<FailCar> cars) {
        this.cars = cars;
    }

    public List<FailCar> getCars() {
        return cars;
    }

    public void setCars(List<FailCar> cars) {
        this.cars = cars;
    }
}
