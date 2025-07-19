package SmartSafetyMap.backend.dtos;

import SmartSafetyMap.backend.entity.LocationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntityDto {

    private String eventDetailType;
    private String eventTypeId;
    private String grade;
    private String message;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime expectedEndTime;
    private LocalDateTime updateTime;
    private LocationType locationType;
    private String sectionCoord;
    private String lanesBlocked;
    private Long xCoord;
    private Long yCoord;


}
