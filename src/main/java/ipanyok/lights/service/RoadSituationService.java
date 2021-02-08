package ipanyok.lights.service;

import ipanyok.lights.model.entity.RoadSituation;
import ipanyok.lights.model.repository.RoadSituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoadSituationService {

    private RoadSituationRepository roadSituationRepository;

    @Autowired
    public RoadSituationService(RoadSituationRepository roadSituationRepository) {
        this.roadSituationRepository = roadSituationRepository;
    }

    @Transactional
    public RoadSituation save(RoadSituation roadSituation) {
        return roadSituationRepository.save(roadSituation);
    }

    @Transactional
    public List<RoadSituation> findAll() {
        return roadSituationRepository.findAll();
    }

    @Transactional
    public List<RoadSituation> findByStatus(RoadSituation.Status status) {
        return roadSituationRepository.findByStatus(status);
    }


}
