package SmartSafetyMap.backend.domain;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.service.NTICXmlService;
import SmartSafetyMap.backend.xmlDto.NTICXmlDto;
import SmartSafetyMap.backend.xmlDto.UTICXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ntic")
public class NTICXmlController {


    @Autowired
    private NTICXmlService nticXmlService;

    @GetMapping
    public String getString() {
        return nticXmlService.fecthXmlAsString("https://openapi.its.go.kr:9443/eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml");
    }

    @GetMapping("/testgetxml")
    public List<NTICXmlDto> testgetxml() {
        return nticXmlService.getXmlAsString();
    }


//    @GetMapping("/testgetEntityDto")
//    public List<EntityDto> testgetEntityDto() throws Exception {
//        return persistenceService.UTICMappingTOEntityDto(utcXmlService.XmlToResponse(
//                utcXmlService.fecthXmlAsString(
//                        "http://www.utic.go.kr/guide/imsOpenData.do?key=quzI6bzs7NwMdwMdWt66pZdePQZjySH7oKYC12zk")));
//    }
//
//    @GetMapping("/testSaveAll")
//    public String testSaveAll() {
//        try{
//            List<EntityDto> dtos = persistenceService.UTICMappingTOEntityDto(utcXmlService.XmlToResponse(
//                    utcXmlService.fecthXmlAsString(
//                            "http://www.utic.go.kr/guide/imsOpenData.do?key=quzI6bzs7NwMdwMdWt66pZdePQZjySH7oKYC12zk")));
//            persistenceService.allSave(dtos);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "success";
//    }


}
