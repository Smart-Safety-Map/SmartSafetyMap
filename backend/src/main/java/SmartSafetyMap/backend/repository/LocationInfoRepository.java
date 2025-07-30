package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.entity.EventTime;
import SmartSafetyMap.backend.entity.LocationInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LocationInfoRepository {

    private final EntityManager em;

    public void save(LocationInfo locationInfo) {
        em.persist(locationInfo);
    }
    @Modifying
    public void delete() {
        em.createQuery("delete from LocationInfo ").executeUpdate();
    }


}
