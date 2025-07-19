package SmartSafetyMap.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
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
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime expectedEndTime;

    private LocalDateTime updateTime;

    @OneToOne(mappedBy = "eventTime", fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;
}
