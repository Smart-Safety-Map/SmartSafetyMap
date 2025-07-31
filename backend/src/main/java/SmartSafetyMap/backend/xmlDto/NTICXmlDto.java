package SmartSafetyMap.backend.xmlDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@JacksonXmlRootElement(localName = "NTIC_info")
@Getter
@Setter
public class NTICXmlDto {

    private String eventType;
    private String eventDetailType;
    private String startDate;
    private String coordX;
    private String coordY;
    private String roadDrcType;
    private String lanesBlockType;
    private String lanesBlocked;
    private String message;
    private String endDate;

}
