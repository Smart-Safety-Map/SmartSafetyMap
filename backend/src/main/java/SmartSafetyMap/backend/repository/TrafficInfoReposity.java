package SmartSafetyMap.backend.repository;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.entity.TrafficInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrafficInfoReposity {

    private final EntityManager em;

    public void save(TrafficInfo trafficInfo) {
        em.persist(trafficInfo);
    }
    @Modifying
    public void delete() {
        em.createQuery("delete from TrafficInfo").executeUpdate();
    }

    public void flush() {
        em.flush();
    }

    //데이터를 전부 가져오는 쿼리문
    public List<EntityDto> getAllAcident() {
        return em.createQuery(
                        "select new SmartSafetyMap.backend.dtos.EntityDto(" +
                                "ei.eventType, ei.eventDetailType, ei.grade, " +
                                "ei.message, et.startTime, et.endTime, et.expectedEndTime, et.updateTime, " +
                                "li.locationType, li.sectionCoord, ti.lanesBlocked, li.xCoord, li.yCoord, " +
                                "ti.roadDrcType) " +
                                "from TrafficInfo ti " +
                                "left join ti.locationInfo li " +
                                "left join ti.eventInfo ei " +
                                "left join ti.eventTime et",
                        EntityDto.class)
                .getResultList();
    }
    //검색기능도 생길수도 있음


}
