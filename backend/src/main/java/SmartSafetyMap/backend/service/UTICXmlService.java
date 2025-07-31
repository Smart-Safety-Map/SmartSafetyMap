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

    //url에있는 xml파일을 String 형태로 변환 코드
    public String fecthXmlAsString(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();
    }


    //String 으로 변환된 Xml파일을 UTICResponse에 파싱하여 자바 객체로 변환 코드
    public List<UTICXmlDto> XmlToDto(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UTICResponse response = xmlMapper.readValue(xml, UTICResponse.class);
        return response.getRecord();
    }

    //String 으로 변환된 Xml파일을 UTICResponse에 파싱하여 자바 객체로 변환 코드
    public UTICResponse XmlToResponse(String xml) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UTICResponse response = xmlMapper.readValue(xml, UTICResponse.class);
        return response;
    }
    //해당 좌표를 받아서 좌표의 구간까지 확인하여 가져오기




    //자바객체로 변환되었는지 확인하기 위한 코드
    public List<UTICXmlDto> getXmlAsString() {
        try {
            return  XmlToDto(fecthXmlAsString(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
