package SmartSafetyMap.backend.dtos;

import SmartSafetyMap.backend.entity.LocationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntityDto {

    private String eventType; //이벤트 유형
    private String eventDetailType; //이벤트 세부 유형
    private String grade; //등급
    private String message; //이벤트 상세 설명
    private LocalDateTime startTime; //발생일시
    private LocalDateTime endTime; //종료일시
    private LocalDateTime expectedEndTime; //종료 예정일시
    private LocalDateTime updateTime; //갱신일시
    private LocationType locationType; // 좌표타입
    private String sectionCoord; //구간 좌표
    private String lanesBlocked; // 차단 차로
    private double xCoord; // x좌표
    private double yCoord; // y좌표
    private String roadDrcType; //도로방향

}
