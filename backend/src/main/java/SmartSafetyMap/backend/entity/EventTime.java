package SmartSafetyMap.backend.entity;

import SmartSafetyMap.backend.dtos.EntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class EventTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_time_id")
    private Long id;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime startTime; //발생일시

    private LocalDateTime endTime; //종료일시

    private LocalDateTime expectedEndTime; //종료예정일시

    private LocalDateTime updateTime; //갱신 일시

    @OneToOne(mappedBy = "eventTime",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;

    public EventTime() {

    }
    public EventTime getEventTime(EntityDto dto) {
       EventTime eventTime = new EventTime();
       eventTime.setStartTime(dto.getStartTime());
       eventTime.setEndTime(dto.getEndTime());
       eventTime.setExpectedEndTime(dto.getExpectedEndTime());
       eventTime.setUpdateTime(dto.getUpdateTime());
       return eventTime;

    }

}
