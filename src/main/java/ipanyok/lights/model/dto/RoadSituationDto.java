package ipanyok.lights.model.dto;

import java.util.ArrayList;
import java.util.List;

public class RoadSituationDto {

    private List<String> northRoadWaitList = new ArrayList<>();
    private List<String> southRoadWaitList = new ArrayList<>();
    private List<String> eastRoadWaitList = new ArrayList<>();
    private List<String> westRoadWaitList = new ArrayList<>();
    private List<String> northRoadGoesList = new ArrayList<>();
    private List<String> southRoadGoesList = new ArrayList<>();
    private List<String> eastRoadGoesList = new ArrayList<>();
    private List<String> westRoadGoesList = new ArrayList<>();

    public List<String> getNorthRoadWaitList() {
        return northRoadWaitList;
    }

    public List<String> getSouthRoadWaitList() {
        return southRoadWaitList;
    }

    public List<String> getEastRoadWaitList() {
        return eastRoadWaitList;
    }

    public List<String> getWestRoadWaitList() {
        return westRoadWaitList;
    }

    public List<String> getNorthRoadGoesList() {
        return northRoadGoesList;
    }

    public List<String> getSouthRoadGoesList() {
        return southRoadGoesList;
    }

    public List<String> getEastRoadGoesList() {
        return eastRoadGoesList;
    }

    public List<String> getWestRoadGoesList() {
        return westRoadGoesList;
    }
}
