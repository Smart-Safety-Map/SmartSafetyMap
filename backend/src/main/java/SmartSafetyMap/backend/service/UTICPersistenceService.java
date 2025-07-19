package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.xmlDto.UTICResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UTICPersistenceService {

    private final UTICXmlService utcXmlService;

    //파싱한걸 엔티티로 매핑하는 로직
    public List<EntityDto> UTICMappingTOEntityDto(UTICResponse utcResponse) {
        return utcResponse.getRecord().stream().map(record -> {
            EntityDto dto = new EntityDto();
            dto.setGrade();
        }).collect(Collectors.toList());
    }

    //엔티티로 매핑한 로직을통해 엔티티들에게 저장하는 로직

    //엔티티로 저장한 로직을 repository를 통해서 db에 저장하는 로직

}
