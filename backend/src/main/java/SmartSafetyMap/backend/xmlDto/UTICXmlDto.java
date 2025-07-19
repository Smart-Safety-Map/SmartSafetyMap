package SmartSafetyMap.backend.xmlDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@JacksonXmlRootElement(localName = "UTIC_info")
@Getter @Setter
public class UTICXmlDto {

    private String incidenteTypeCd;
    private String incidenteSubTypeCd;
    private String locationDataX;
    private String locationDataY;
    private String incidenteGradeCd;
    private String incidentTitle;
    private String incTrafficCode;
    private String startDate;
    private String endDate;
    private String lane;
    private String controlType;
    private String updateDate;
}


