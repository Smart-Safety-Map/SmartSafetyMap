package SmartSafetyMap.backend.service;

import SmartSafetyMap.backend.xmlDto.NTICResponse;
import SmartSafetyMap.backend.xmlDto.NTICXmlDto;
import SmartSafetyMap.backend.xmlDto.UTICResponse;
import SmartSafetyMap.backend.xmlDto.UTICXmlDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class NTICXmlService {


        String url = "https://openapi.its.go.kr:9443/eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml";

        //url에있는 xml파일을 String 형태로 변환 코드
        public String fecthXmlAsString(String url) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
            return response.getBody();
        }


        //String 으로 변환된 Xml파일을 UTICResponse에 파싱하여 자바 객체로 변환 코드
        public NTICResponse XmlToDto(String xml) throws Exception {
            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return xmlMapper.readValue(xml, NTICResponse.class);
        }



        //자바객체로 변환되었는지 확인하기 위한 코드
        public List<NTICXmlDto> getXmlAsString() {
            try {
                String xml = fecthXmlAsString(url);
                System.out.println("Received XML:\n" + xml);  // 실제 XML 확인

                NTICResponse nticResponse = XmlToDto(xml); // XmlMapper 사용

                if (nticResponse == null || nticResponse.getBody() == null) {
                    System.out.println("No NTIC response");
                    return Collections.emptyList();
                }

                List<NTICXmlDto> items = nticResponse.getBody().getItems();

                return (items != null) ? items : Collections.emptyList();

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }





