package g12.p2.cst438.g12_springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    /**
     * SecurityFilterChain for the API. Set to require authentication via Google for all API routes.
     * @param http Spring Boot HttpSecurity that configures security settings for the API.
     * @return SecurityFilterChain that is configured based on our security settings
     * @throws Exception not used currently
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin/**", "/patchExercise/**", "/add**", "/delete**", "/get**", "/update**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .oauth2Login(oAuth2Login -> {
                    oAuth2Login.defaultSuccessUrl("http://localhost:3000/dashboard");
                    oAuth2Login.userInfoEndpoint(userInfo -> {
                        userInfo.userService(customOAuth2UserService);
                    });
                })
                .cors(cors -> {
                    cors.configurationSource(getUrlBasedCorsConfigurationSource());
                });

        return http.build();
    }

    private static UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
