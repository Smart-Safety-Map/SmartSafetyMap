package SmartSafetyMap.backend.controller;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.service.NTICXmlService;
import SmartSafetyMap.backend.service.InquiryService;
import SmartSafetyMap.backend.service.NticPersistenceService;
import SmartSafetyMap.backend.xmlDto.NTICXmlDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ntic")
public class NTICXmlController {


    @Autowired
    private EntityManager em;
    @Autowired
    private NTICXmlService nticXmlService;
    @Autowired
    private NticPersistenceService nticPersistenceService;
    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    public String getString() {
        return nticXmlService.fecthXmlAsString("https://openapi.its.go.kr:9443/eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml");
    }

    @GetMapping("/testgetxml")
    public List<NTICXmlDto> testgetxml() {
        return nticXmlService.getXmlAsString();
    }


    @GetMapping("/testgetEntityDto")
    public List<EntityDto> testgetEntityDto() throws Exception {
        return nticPersistenceService.NTICMappingToEntityDto(nticXmlService.XmlToDto(
                nticXmlService.fecthXmlAsString("https://openapi.its.go.kr:9443/eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml")));
    }

    @GetMapping("/testSaveAll")
    public String testSaveAll() {
        nticPersistenceService.allDelete();
        try{
            List<EntityDto> entityDtos = nticPersistenceService.NTICMappingToEntityDto(nticXmlService.XmlToDto(
                    nticXmlService.fecthXmlAsString("https://openapi.its.go.kr:9443/eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml")));

            nticPersistenceService.allSave(entityDtos);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    @GetMapping("/testDBConnection")
    public String testDBConnection() {
        nticPersistenceService.testDbConnection();
        return "success";
    }
    @GetMapping("/getAllAcident")
    public List<EntityDto> getAllAcident() {
        List<EntityDto> acidentList = inquiryService.getAllAcident();
        return acidentList;
    }


}
