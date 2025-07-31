package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.entity.EventInfo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EventInfoRepository {

    @Autowired
    private EntityManager em;

    public void save(EventInfo eventInfo) {
        em.persist(eventInfo);
    }

    @Modifying
    public void delete() {
        em.createQuery("delete from EventInfo ").executeUpdate();
    }



}
