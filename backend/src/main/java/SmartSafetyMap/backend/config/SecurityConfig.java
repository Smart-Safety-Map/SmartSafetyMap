package SmartSafetyMap.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()   // 모든 요청을 허용23
                )
                .csrf(csrf -> csrf.disable()); // (옵션) 개발 편의를 위해 CSRF 비활성화
        return http.build();
    }
}
