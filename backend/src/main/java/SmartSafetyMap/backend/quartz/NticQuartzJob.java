package SmartSafetyMap.backend.quartz;

import SmartSafetyMap.backend.dtos.EntityDto;
import SmartSafetyMap.backend.service.NTICXmlService;
import SmartSafetyMap.backend.service.NticPersistenceService;
import SmartSafetyMap.backend.xmlDto.NTICResponse;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NticQuartzJob implements Job {

    @Autowired
    private  NticPersistenceService nticPersistenceService;
    @Autowired
    private  NTICXmlService nticXmlService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try{
            nticPersistenceService.allDelete();
            System.out.println("삭제 진행됨");

            NTICResponse nticResponse = nticXmlService.XmlToDto(nticXmlService.fecthXmlAsString("https://openapi.its.go.kr:9443/" +
                    "eventInfo?apiKey=70cc70fa9a8044b08ea5b54a5dd42218&type=all&eventType=all&getType=xml"));
            List<EntityDto> entityDtos = nticPersistenceService.NTICMappingToEntityDto(nticResponse);
            nticPersistenceService.allSave(entityDtos);
            System.out.println("저장 진행됨");
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
