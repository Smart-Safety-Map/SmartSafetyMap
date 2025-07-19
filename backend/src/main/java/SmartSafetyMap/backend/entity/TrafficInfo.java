package SmartSafetyMap.backend.entity;

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

    @NonNull
    @Column(nullable = false)
    private String roadDirection;

    @NonNull
    @Column(nullable = false)
    private String lanesBlocked;



}
