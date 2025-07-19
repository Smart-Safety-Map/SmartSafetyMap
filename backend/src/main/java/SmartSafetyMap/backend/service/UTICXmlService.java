package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.xmlDto.UTICResponse;
import SmartSafetyMap.backend.xmlDto.UTICXmlDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UTICXmlService {

    String url = "http://www.utic.go.kr/guide/imsOpenData.do?key=quzI6bzs7NwMdwMdWt66pZdePQZjySH7oKYC12zk";

    public String fecthXmlAsString(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();
    }


    public List<UTICXmlDto> XmlToDto(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UTICResponse response = xmlMapper.readValue(xml, UTICResponse.class);
        return response.getRecord();
    }

    public List<UTICXmlDto> getXmlAsString() {
        try {
            return  XmlToDto(fecthXmlAsString(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
