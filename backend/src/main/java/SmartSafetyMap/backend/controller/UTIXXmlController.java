package SmartSafetyMap.backend.controller;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.service.UTICPersistenceService;
import SmartSafetyMap.backend.service.UTICXmlService;
import SmartSafetyMap.backend.xmlDto.UTICXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UTIXXmlController {

    @Autowired
    private UTICXmlService utcXmlService;

    @Autowired
    private UTICPersistenceService persistenceService;

    @GetMapping("/testgetxml")
    public List<UTICXmlDto> testgetxml() {
        return utcXmlService.getXmlAsString();
    }


    @GetMapping("/testgetEntityDto")
    public List<EntityDto> testgetEntityDto() throws Exception {
        return persistenceService.UTICMappingTOEntityDto(utcXmlService.XmlToResponse(
                utcXmlService.fecthXmlAsString(
                        "http://www.utic.go.kr/guide/imsOpenData.do?key=quzI6bzs7NwMdwMdWt66pZdePQZjySH7oKYC12zk")));
    }

    @GetMapping("/testSaveAll")
    public String testSaveAll() {
        try{
            List<EntityDto> dtos = persistenceService.UTICMappingTOEntityDto(utcXmlService.XmlToResponse(
                    utcXmlService.fecthXmlAsString(
                            "http://www.utic.go.kr/guide/imsOpenData.do?key=quzI6bzs7NwMdwMdWt66pZdePQZjySH7oKYC12zk")));
            persistenceService.allSave(dtos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }


}
