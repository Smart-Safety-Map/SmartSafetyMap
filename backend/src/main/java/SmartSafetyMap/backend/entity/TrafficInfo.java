package SmartSafetyMap.backend.entity;

import SmartSafetyMap.backend.dtos.EntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Getter @Setter
public class TrafficInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_info_id")
    private EventInfo eventInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationInfo locationInfo;

    private String roadDrcType; //차단차로 정보

    private String lanesBlocked; //차단차로 정보

    public TrafficInfo() {

    }

    public static TrafficInfo fromDto(EntityDto dto, EventTime eventTime,
                       LocationInfo locationInfo, EventInfo eventInfo) {
       TrafficInfo trafficInfo = new TrafficInfo();
       trafficInfo.setEventInfo(eventInfo);
       trafficInfo.setEventTime(eventTime);
       trafficInfo.setLocationInfo(locationInfo);
       trafficInfo.setLanesBlocked(dto.getLanesBlocked());
       trafficInfo.setRoadDrcType(dto.getRoadDrcType());
       return trafficInfo;
    }

}
