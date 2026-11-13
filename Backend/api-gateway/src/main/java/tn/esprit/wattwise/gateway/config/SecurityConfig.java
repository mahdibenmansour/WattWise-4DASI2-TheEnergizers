package tn.esprit.wattwise.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * JWT validation with Keycloak.
 * Session 4: security is off by default (wattwise.security.enabled=false) so the
 * hello endpoints can be shown without Keycloak. Set it to true once the
 * "wattwise" realm exists.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${wattwise.security.enabled:false}")
    private boolean securityEnabled;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        if (securityEnabled) {
            http.authorizeExchange(exchanges -> exchanges
                            .pathMatchers("/actuator/health", "/actuator/info").permitAll()
                            .anyExchange().authenticated())
                    .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        } else {
            http.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll());
        }
        return http.build();
    }
}
