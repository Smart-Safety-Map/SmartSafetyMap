package SmartSafetyMap.backend.entity;

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

    private String eventTypeId;

    private String evenDetailType;

    @NonNull
    @Column(nullable = false)
    private String message;

    @NonNull
    @Column(nullable = false)
    private String grade;


    @OneToOne(mappedBy = "eventInfo", fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;

}
