package SmartSafetyMap.backend.entity;

import SmartSafetyMap.backend.dtos.EntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter
public class EventInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evnet_info_id")
    private Long id;

    private String eventType; //이벤트 유형

    private String evenDetailType;//이벤트  세부 유형

    @NonNull
    @Column(nullable = false)
    private String message; //이벤트 상세 설명

    private String grade; //위험 등급


    @OneToOne(mappedBy = "eventInfo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;

    public EventInfo() {

    }

    public EventInfo getEventInfo(EntityDto dto) {
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventType(dto.getEventType());
        eventInfo.setMessage(dto.getMessage());
        eventInfo.setGrade(dto.getGrade());
        return eventInfo;

    }


}
