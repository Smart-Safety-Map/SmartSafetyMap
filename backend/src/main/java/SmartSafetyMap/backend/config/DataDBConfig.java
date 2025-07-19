package SmartSafetyMap.backend.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "SmartSafetyMap.backend.repository",
        entityManagerFactoryRef = "dataEntityManager",
        transactionManagerRef = "dataTransactionManager"
)
public class DataDBConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.domain")
    public DataSource dataDBSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean dataEntityManager(
            @Qualifier("dataDBSource")DataSource dataDBSource) {
        LocalContainerEntityManagerFactoryBean em = new
                LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataDBSource);
        em.setPackagesToScan("SmartSafetyMap.backend.entity");
        em. setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager dataTransactionManager(
            @Qualifier("dataEntityManager") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}