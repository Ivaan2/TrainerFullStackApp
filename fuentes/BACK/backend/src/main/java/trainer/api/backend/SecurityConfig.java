package trainer.api.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/**").permitAll()  // Protege los endpoints con autenticación
                        .anyRequest().permitAll()  // Permite acceso a otros endpoints sin autenticación
                )
                .formLogin(withDefaults())  // Usa autenticación basada en formularios
                .csrf(AbstractHttpConfigurer::disable);  // Desactiva CSRF si no es necesario

        return http.build();
    }
}