package SmartSafetyMap.backend.xmlDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@JacksonXmlRootElement(localName = "UTIC_info")
@Getter @Setter
public class UTICXmlDto {

    private String incidenteTypeCd; //돌발유형
    private String incidenteSubTypeCd; //돌발 세부 유형
    private String locationDataX; //x좌표
    private String locationDataY; //y좌표
  //필요할지 모르겠음  private String incidenteGradeCd; // 소통 등급
    private String incidentTitle; //돌발제목
    private String incTrafficCode; //돌발등급
    private String startDate; // 발생일
    private String endDate; //종료일
    private String lane; //차선
    private String controlType; //통제 정보
    private String updateDate; // 돌발갱신 일지
}


