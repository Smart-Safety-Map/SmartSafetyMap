package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.entity.*;
import SmartSafetyMap.backend.repository.EventInfoRepository;
import SmartSafetyMap.backend.repository.EventTimeRepository;
import SmartSafetyMap.backend.repository.LocationInfoRepository;
import SmartSafetyMap.backend.repository.TrafficInfoReposity;
import SmartSafetyMap.backend.xmlDto.UTICGrade;
import SmartSafetyMap.backend.xmlDto.UTICIcnType;
import SmartSafetyMap.backend.xmlDto.UTICResponse;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UTICPersistenceService {


    private final EventTimeRepository eventTimeRepository;
    private final EventInfoRepository eventInfoRepository;
    private final LocationInfoRepository locationInfoRepository;
    private  final TrafficInfoReposity trafficInfoReposity;
    private final UTICXmlService utcXmlService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");

    public List<EntityDto> UTICMappingTOEntityDto(UTICResponse utcResponse) {
        return utcResponse.getRecord().stream().map(record -> {
            EntityDto dto = new EntityDto();

            // Double 좌표 파싱 (널, 빈문자열 체크)
            String xCoordStr = record.getLocationDataX();
            dto.setYCoord(
                    xCoordStr == null || xCoordStr.trim().isEmpty() ? null : Double.parseDouble(xCoordStr)
            );
            String yCoordStr = record.getLocationDataY();
            dto.setXCoord(
                    yCoordStr == null || yCoordStr.trim().isEmpty() ? null : Double.parseDouble(yCoordStr)
            );

            // Grade 변환 (널일 때 null, 값이 있으면 그대로)
            String gradeCode = record.getIncTrafficCode();
            dto.setGrade(
                    gradeCode == null || gradeCode.trim().isEmpty() ? null : UTICGrade.getDescriptionByCode(gradeCode)
            );

            // 날짜 파싱 시 널, 빈문자열 처리와 공백 정규화
            String startDate = record.getStartDate();
            dto.setStartTime(
                    startDate == null || startDate.trim().isEmpty() ? null : LocalDateTime.parse(startDate.replaceAll(" +", " ").trim(), formatter)
            );
            String endDate = record.getEndDate();
            dto.setEndTime(
                    endDate == null || endDate.trim().isEmpty() ? null : LocalDateTime.parse(endDate.replaceAll(" +", " ").trim(), formatter)
            );
            String updateDate = record.getUpdateDate();
            dto.setUpdateTime(
                    updateDate == null || updateDate.trim().isEmpty() ? null : LocalDateTime.parse(updateDate.replaceAll(" +", " ").trim(), formatter)
            );

            // 메시지 (널 체크)
            String incidentTitle = record.getIncidentTitle();
            dto.setMessage(
                    incidentTitle == null || incidentTitle.trim().isEmpty() ? null : incidentTitle
            );

            // 이벤트 유형, 상세유형
            String typeCd = record.getIncidenteTypeCd();
            String subTypeCd = record.getIncidenteSubTypeCd();

            dto.setEventDetailType(
                    typeCd == null || subTypeCd == null || typeCd.trim().isEmpty() || subTypeCd.trim().isEmpty()
                            ? null
                            : UTICIcnType.getDescriptionByCodes(Integer.parseInt(typeCd), Integer.parseInt(subTypeCd))
            );
            dto.setEventType(
                    typeCd == null || typeCd.trim().isEmpty()
                            ? null
                            : UTICIcnType.getTypeByMainCode(Integer.parseInt(typeCd))
            );

            // 차로: 널 체크
            String lane = record.getLane();
            dto.setLanesBlocked(
                    lane == null || lane.trim().isEmpty() ? null : lane
            );

            dto.setLocationType(LocationType.COORD);

            return dto;
        }).collect(Collectors.toList());
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
            trafficInfoReposity.flush();
        }


    }
    //모든 데이터 삭제 로직 공통로직이 될 예정 여기부터 현재 아이피 변경으로 테스트 불가능
    @Transactional
    public void allDelete(){
        trafficInfoReposity.delete();
        eventInfoRepository.delete();
        locationInfoRepository.delete();
        eventTimeRepository.delete();
    }
}
