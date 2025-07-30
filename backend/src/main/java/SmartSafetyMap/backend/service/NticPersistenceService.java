package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.entity.*;
import SmartSafetyMap.backend.repository.EventInfoRepository;
import SmartSafetyMap.backend.repository.EventTimeRepository;
import SmartSafetyMap.backend.repository.LocationInfoRepository;
import SmartSafetyMap.backend.repository.TrafficInfoReposity;
import SmartSafetyMap.backend.xmlDto.NTICResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NticPersistenceService {

    private final EventTimeRepository eventTimeRepository;
    private final EventInfoRepository eventInfoRepository;
    private final LocationInfoRepository locationInfoRepository;
    private  final TrafficInfoReposity trafficInfoReposity;
    private final UTICXmlService utcXmlService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /* 1. NTICXmlDto 리스트를 EntityDto로 변환 */
    public List<EntityDto> NTICMappingToEntityDto(NTICResponse nticResponse) {
        if (nticResponse == null || nticResponse.getBody() == null || nticResponse.getBody().getItems() == null) {
            return List.of();
        }

        return nticResponse.getBody().getItems().stream().map(record -> {
            EntityDto dto = new EntityDto();

            // 좌표 파싱
            String xCoordStr = record.getCoordX();
            dto.setXCoord(
                    xCoordStr == null || xCoordStr.trim().isEmpty() ? null : Double.parseDouble(xCoordStr)
            );
            String yCoordStr = record.getCoordY();
            dto.setYCoord(
                    yCoordStr == null || yCoordStr.trim().isEmpty() ? null : Double.parseDouble(yCoordStr)
            );

            // 이벤트 유형, 상세유형 직접 세팅 (코드 변환 필요하면 enum 매핑 추가)
            dto.setEventType(
                    record.getEventType() == null || record.getEventType().trim().isEmpty() ? null : record.getEventType()
            );
            dto.setEventDetailType(
                    record.getEventDetailType() == null || record.getEventDetailType().trim().isEmpty() ? null : record.getEventDetailType()
            );

            // 날짜 파싱: 널, 빈문자, 정규화
            String startDate = record.getStartDate();
            dto.setStartTime(
                    startDate == null || startDate.trim().isEmpty() ? null : LocalDateTime.parse(startDate.replaceAll(" +", " ").trim(), formatter)
            );
            String endDate = record.getEndDate();
            dto.setEndTime(
                    endDate == null || endDate.trim().isEmpty() ? null : LocalDateTime.parse(endDate.replaceAll(" +", " ").trim(), formatter)
            );

            // 메시지
            dto.setMessage(
                    record.getMessage() == null || record.getMessage().trim().isEmpty() ? "정보없음 " : record.getMessage()
            );

            // 차로 데이터
            dto.setLanesBlocked(
                    record.getLanesBlocked() == null || record.getLanesBlocked().trim().isEmpty() ? null : record.getLanesBlocked()
            );

            dto.setRoadDrcType(
                    record.getRoadDrcType() == null || record.getRoadDrcType().trim().isEmpty() ? null : record.getRoadDrcType()
            );

            // 기타 타입 세팅 (코드화 필요시 적절히 매핑)
            dto.setLocationType(LocationType.COORD);

            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void testDbConnection() {
        TrafficInfo trafficInfo = new TrafficInfo();
        trafficInfo.setLanesBlocked("1차로");
        trafficInfoReposity.save(trafficInfo);
    }



    //엔티티로 매핑한 로직을통해 엔티티들에게 저장하는 로직 db저장못하는 문제 해결 못함
    @Transactional
    public void allSave(List<EntityDto> entityDtos) {
        int a = 1;
        for (EntityDto entityDto : entityDtos) {
            //3개 엔티티들 저장하는 로직 + trafficinfo에 저장 그리고 repository에 세이브까지
            //시간 괜찮다면 배치로직입문까지
            trafficInfoReposity.save(TrafficInfo.fromDto(entityDto,
                    new EventTime().getEventTime(entityDto),
                    new LocationInfo().getLocationInfo(entityDto),
                    new EventInfo().getEventInfo(entityDto)));

        }
        trafficInfoReposity.flush();


    }
    @Transactional
    public void allDelete(){
        trafficInfoReposity.delete();
        eventInfoRepository.delete();
        locationInfoRepository.delete();
        eventTimeRepository.delete();
        trafficInfoReposity.flush();
    }
}
