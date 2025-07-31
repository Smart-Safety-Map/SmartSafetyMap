package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.repository.TrafficInfoReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private TrafficInfoReposity trafficInfoReposity;

    //데이터를 전부 가져오는 로직 (일정범위 내에서만 움직이게 할것인지 생각.
    public List<EntityDto> getAllAcident(){
        List<EntityDto> allAcident = trafficInfoReposity.getAllAcident();
        return allAcident;
    }


}
