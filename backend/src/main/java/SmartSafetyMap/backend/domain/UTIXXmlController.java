package SmartSafetyMap.backend.domain;

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

    @GetMapping("/testgetxml")
    public List<UTICXmlDto> testgetxml() {
        return utcXmlService.getXmlAsString();
    }


}
