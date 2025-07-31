package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.repository.TrafficInfoReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NticInquiryService {

    @Autowired
    private TrafficInfoReposity trafficInfoReposity;


    public List<EntityDto> getAllAcident(){
        List<EntityDto> allAcident = trafficInfoReposity.getAllAcident();
        return allAcident;
    }


}
