package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.entity.EventTime;
import SmartSafetyMap.backend.entity.TrafficInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrafficInfoReposity {

    private final EntityManager em;

    public void save(TrafficInfo trafficInfo) {
        em.persist(trafficInfo);
    }

    public void allsave(List<TrafficInfo> trafficInfos) {
        em.persist(trafficInfos);
    }

}
