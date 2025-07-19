package SmartSafetyMap.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter
public class LocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @NonNull
    @Column(nullable = false)
    private LocationType locationType;

    private double xCoord;

    private double yCoord;

    private String sectionCoord;

    @OneToOne(mappedBy = "locationInfo", fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;


}
