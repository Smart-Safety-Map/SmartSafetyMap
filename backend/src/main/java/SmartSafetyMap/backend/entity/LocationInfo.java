package SmartSafetyMap.backend.entity;

import SmartSafetyMap.backend.dtos.EntityDto;
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
    private LocationType locationType; //위치타입 (구간좌표, 위치좌표)

    private double xCoord; //x좌표

    private double yCoord; //y좌표

    private String sectionCoord; //구간 좌표

    @OneToOne(mappedBy = "locationInfo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TrafficInfo trafficInfo;

    public LocationInfo() {

    }

    public LocationInfo getLocationInfo(EntityDto dto) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setLocationType(dto.getLocationType());
        locationInfo.setXCoord(dto.getXCoord());
        locationInfo.setYCoord(dto.getYCoord());
        locationInfo.setSectionCoord(dto.getSectionCoord());
        return locationInfo;

    }


}
