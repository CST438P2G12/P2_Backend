package g12.p2.cst438.g12_springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * SecurityFilterChain for the API. Set to require authentication via Google for all API routes.
     * @param http Spring Boot HttpSecurity that configures security settings for the API.
     * @return SecurityFilterChain that is configured based on our security settings
     * @throws Exception not used currently
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .oauth2Login(oAuth2Login -> {
                });

        return http.build();
    }
}
