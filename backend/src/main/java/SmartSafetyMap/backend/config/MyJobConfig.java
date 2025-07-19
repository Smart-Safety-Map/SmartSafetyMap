package SmartSafetyMap.backend.config;

import SmartSafetyMap.backend.service.UTICXmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class MyJobConfig {

    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private UTICXmlService utcXmlService;



}
