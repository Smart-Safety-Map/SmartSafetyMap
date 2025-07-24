package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.entity.EventInfo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventInfoRepository {

    @Autowired
    private EntityManager em;

    public void save(EventInfo eventInfo) {
        em.persist(eventInfo);
    }



}
