package ipanyok.lights.model.repository;

import ipanyok.lights.model.entity.RoadSituation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadSituationRepository extends JpaRepository<RoadSituation, Long> {
    List<RoadSituation> findByStatus(RoadSituation.Status status);
}
