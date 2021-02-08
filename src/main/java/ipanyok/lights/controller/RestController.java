package ipanyok.lights.controller;

import ipanyok.lights.model.Car;
import ipanyok.lights.model.Road;
import ipanyok.lights.model.dto.CarsOnRedDto;
import ipanyok.lights.model.dto.FailCar;
import ipanyok.lights.model.dto.RoadSituationDto;
import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.service.RoadSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/roadsituation")
public class RestController {

    private RoadSituationService roadSituationService;
    private Road road;

    @Autowired
    public RestController(RoadSituationService roadSituationService, Road road) {
        this.roadSituationService = roadSituationService;
        this.road = road;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoadSituationDto getCurrentRoadSituation() {
        List<RoadSituation> roadSituationList = roadSituationService.findAll();
        RoadSituationDto roadSituationDto = new RoadSituationDto();

        roadSituationDto.getEastRoadWaitList().addAll(getWaitingCars(road.getEastRoad()));

        roadSituationDto.getWestRoadWaitList().addAll(getWaitingCars(road.getWestRoad()));

        roadSituationDto.getNorthRoadWaitList().addAll(getWaitingCars(road.getNorthRoad()));

        roadSituationDto.getSouthRoadWaitList().addAll(getWaitingCars(road.getSouthRoad()));

        roadSituationDto.getEastRoadGoesList().addAll(getFilteredCar(roadSituationList, RoadSituation.Status.GOES, RoadSituation.Roads.EAST));

        roadSituationDto.getWestRoadGoesList().addAll(getFilteredCar(roadSituationList, RoadSituation.Status.GOES, RoadSituation.Roads.WEST));

        roadSituationDto.getNorthRoadGoesList().addAll(getFilteredCar(roadSituationList, RoadSituation.Status.GOES, RoadSituation.Roads.NORTH));

        roadSituationDto.getSouthRoadGoesList().addAll(getFilteredCar(roadSituationList, RoadSituation.Status.GOES, RoadSituation.Roads.SOUTH));

        return roadSituationDto;
    }

    @GetMapping(value = "/get/carsonred", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarsOnRedDto getCarsOnRedLight() {
        List<RoadSituation> carsOnRed = roadSituationService.findByStatus(RoadSituation.Status.GOES_ON_RED);
        List<FailCar> resultList = new ArrayList<>();
        carsOnRed.forEach(roadSituation -> {
            FailCar failCar = new FailCar(roadSituation.getCarName(), roadSituation.getRoads(), roadSituation.getTime());
            resultList.add(failCar);
        });
        return new CarsOnRedDto(resultList);
    }

    @GetMapping(value = "/get/carsonred/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CarsOnRedDto getCarsOnRedLightByRange(@PathVariable("from") String fromTime, @PathVariable("to") String toTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm");
        LocalDateTime timeFrom = LocalDateTime.parse(fromTime, formatter);
        LocalDateTime timeTo = LocalDateTime.parse(toTime, formatter);
        List<RoadSituation> carsOnRed = roadSituationService.findByStatus(RoadSituation.Status.GOES_ON_RED);
        List<FailCar> resultList = new ArrayList<>();
        carsOnRed.stream().filter(roadSituation -> roadSituation.getTime().isAfter(timeFrom) && roadSituation.getTime().isBefore(timeTo)).forEach(roadSituation -> {
            FailCar failCar = new FailCar(roadSituation.getCarName(), roadSituation.getRoads(), roadSituation.getTime());
            resultList.add(failCar);
        });
        return new CarsOnRedDto(resultList);
    }

    private List<String> getFilteredCar(List<RoadSituation> roadSituations, RoadSituation.Status status, RoadSituation.Roads roads) {
        return roadSituations.stream()
                .filter(roadSituation -> roadSituation.getStatus().equals(status) && roadSituation.getRoads().equals(roads))
                .map(RoadSituation::getCarName)
                .collect(Collectors.toList());
    }

    private List<String> getWaitingCars(List<Car> cars) {
        return cars.stream().map(Car::getName).collect(Collectors.toList());
    }

}
