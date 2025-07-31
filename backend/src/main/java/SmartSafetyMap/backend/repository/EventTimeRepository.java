package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.entity.EventInfo;
import SmartSafetyMap.backend.entity.EventTime;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EventTimeRepository {

    private final EntityManager em;

    public void save(EventTime eventTime) {
        em.persist(eventTime);
    }
    @Modifying
    public void delete() {
        em.createQuery("delete from EventTime ").executeUpdate();
    }
}
